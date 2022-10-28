package dev.licensex.manager.files;

import dev.licensex.manager.exceptions.LicenseManagerXException;
import dev.licensex.manager.utils.crypto.AES;
import dev.licensex.manager.utils.jnic;
import lombok.Getter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Getter
public class LicenseXFile {
    protected final String path;
    protected final String name;
    protected final List<String> content;
    protected File file;
    protected boolean newFile = false;
    protected boolean encryption = true;

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
                newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        readFile();
    }

    public LicenseXFile(String name_, String path_, boolean encryption_) {
        this.name = (name_.endsWith(".lx") ? name_ : name_ + ".lx");
        this.path = (path_.endsWith(".lx") ? path_ : path_ + File.separator + name);
        this.content = new ArrayList<>();
        this.encryption = encryption_;

        if (!path.endsWith(".lx")) {
            System.out.println("can't load none lx file");
            return;
        }
        file = new File(path);
        if (!file.exists()) {
            try {
                newFile = file.createNewFile();
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
                newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new LicenseManagerXException("File already exists");
        }

        writeFile();
    }

    public LicenseXFile(String name_, String path_, List<String> content, boolean encryption_) throws LicenseManagerXException {
        this.name = (name_.endsWith(".lx") ? name_ : name_ + ".lx");
        this.path = (path_.endsWith(".lx") ? path_ : path_ + File.separator + name);
        this.content = content;
        this.encryption = encryption_;

        if (!path.endsWith(".lx")) {
            System.out.println("can't load none lx file");
            return;
        }
        file = new File(path);
        if (!file.exists()) {
            try {
                newFile = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            throw new LicenseManagerXException("File already exists");
        }

        writeFile();
    }

    private List<String> decryptContent(List<String> content) {
        if (encryption) {
            List<String> decryptList = new ArrayList<>();
            String encKey = generateEncryptionKey();
            for (String line : content)
                decryptList.add(AES.decrypt(line, encKey));
            return decryptList;
        }
        return content;
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
            while ((strLine = br.readLine()) != null) {
                initialContent.add(strLine);
            }
            fstream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> decryptedContent = decryptContent(initialContent);
        List<String> decompressedContent = decompressContent(decryptedContent);

        content.addAll(decompressedContent);
    }

    private List<String> encryptContent(List<String> content) {
        if (encryption) {
            List<String> encryptedList = new ArrayList<>();
            String encKey = generateEncryptionKey();
            for (String line : content)
                if (line.length() != 0)
                    encryptedList.add(AES.encrypt(line, encKey));
            return encryptedList;
        }
        return content;
    }

    @jnic
    private String generateEncryptionKey() {
        // ToDo improve this method, use more data to make the enc key
        StringBuilder encKey = new StringBuilder();
        for (int i = 0; i < (64 / 8); i++) {
            encKey.append(i % 2 == 0 ? Character.toUpperCase(getNameChar(name, i)) : getNameChar(name, i));
        }
        return encKey.toString();
    }

    private char getNameChar(String name, int index) {
        if (index >= name.length())
            return name.charAt(0);
        return name.charAt(index);
    }

    private List<String> compressContent() {
        // ToDo implement compression
        return content;
    }

    protected void updatePhysicalFile() {
        writeFile();
    }

    private void writeFile() {
        List<String> processedContent = encryptContent(compressContent());

        File file = new File(path);
        file.delete();

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