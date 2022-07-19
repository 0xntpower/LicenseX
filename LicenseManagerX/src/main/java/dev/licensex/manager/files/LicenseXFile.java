package dev.licensex.manager.files;

import dev.licensex.manager.exceptions.LicenseManagerXException;
import dev.licensex.manager.utils.AES;
import dev.licensex.manager.utils.jnic;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LicenseXFile {
    private final String path;
    private final String name;
    private final List<String> content;
    private File file;

    public LicenseXFile(String name_, String path_) {
        this.name = (name_.endsWith(".lx") ? name_ : name_ + ".lx");
        this.path = (path_.endsWith(".lx") ? path_ : path_ + File.separator + name);
        this.content = new ArrayList<>();

        if (!path.endsWith(".lx")) {
            System.out.println("can't load none lx file");
            return;
        }
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        readFile();
    }

    public LicenseXFile(String name_, String path_, List<String> content) throws LicenseManagerXException {
        this.name = (name_.endsWith(".lx") ? name_ : name_ + ".lx");
        this.path = (path_.endsWith(".lx") ? path_ : path_ + File.separator + name);
        this.content = content;

        if (!path.endsWith(".lx")) {
            System.out.println("can't load none lx file");
            return;
        }
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new LicenseManagerXException("File already exists");
        }

        writeFile();
    }

    private List<String> decryptContent(List<String> content) {
        List<String> decryptList = new ArrayList<>();
        String encKey = generateEncryptionKey();
        for (String line : content)
            decryptList.add(AES.decrypt(line, encKey));
        return decryptList;
    }

    private List<String> decompressContent(List<String> content) {
        // ToDo implement compression
        return content;
    }

    private void readFile() {
        List<String> initialContent = new ArrayList<>();

        try {
            FileInputStream fstream;
            fstream = new FileInputStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            while ((strLine = br.readLine()) != null)   {
                initialContent.add(strLine);
            }
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> decryptedContent = decryptContent(initialContent);
        List<String> decompressedContent = decompressContent(decryptedContent);

        content.addAll(decompressedContent);

        for (String line : content)
            System.out.println(line);
    }

    private List<String> encryptContent(List<String> content) {
        List<String> encryptedList = new ArrayList<>();
        String encKey = generateEncryptionKey();
        for (String line : content)
            encryptedList.add(AES.encrypt(line, encKey));
        return encryptedList;
    }

    @jnic
    private String generateEncryptionKey() {
        // ToDo complete this method and make it more complicated
        StringBuilder encKey = new StringBuilder();
        for (int i = 0; i < (64 / 8); i++) {
            encKey.append(i % 2 == 0 ? Character.toUpperCase(name.charAt(0)) : name.charAt(0));
        }
        return encKey.toString();
    }

    private List<String> compressContent() {
        // ToDo implement compression
        return content;
    }

    private void writeFile() {
        List<String> processedContent = encryptContent(compressContent());

        File file = new File(path);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (String line : processedContent)
                writer.write(line + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static boolean exists(String path) {
        File file = new File(path);
        return file.exists();
    }
}
