package dev.licensex.server.filesys;

import dev.licensex.server.exceptions.LicenseServerXException;
import dev.licensex.server.utils.FilenameUtils;

import java.util.List;

public class LXConfig extends LicenseXFile {

    public LXConfig(String path) {
        super(FilenameUtils.getName(path), FilenameUtils.getFullPathNoEndSeparator(path));
    }

    public LXConfig(String path, boolean encrypt) {
        super(FilenameUtils.getName(path), FilenameUtils.getFullPathNoEndSeparator(path), encrypt);
    }

    public LXConfig(String path, List<String> content) throws LicenseServerXException {
        super(FilenameUtils.getName(path), FilenameUtils.getPath(path), content);
    }

    public LXConfig(String path, List<String> content, boolean encrypt) throws LicenseServerXException {
        super(FilenameUtils.getName(path), FilenameUtils.getPath(path), content, encrypt);
    }

    public void set(String key, Object val) {
        if (val == null) {
            setValueForKey(key, null);
        } else {
            Object value = get(key);
            if (value == null) {
                // key doesn't exist, add new key and value
                content.add(genLine(key, val));
            } else {
                // key already exist, override its value
                setValueForKey(key, val);
            }
        }
        updatePhysicalFile();
    }

    public Object get(String key) {
        for (String str : content) {
            if (str.contains(":")) {
                String keyval = str.substring(0, str.indexOf(':'));
                if (keyval.equals(key))
                    return str.substring(str.indexOf(':') + 2);
            }
        }
        return null;
    }

    public String getString(String key) {
        return get(key) + "";
    }

    private void setValueForKey(String key, Object value) {
        int index = -999;

        for (int i = 0; i < content.size(); i++) {
            String keyval = content.get(i).substring(0, content.get(i).indexOf(':'));
            if (keyval.equals(key)) {
                index = i;
                break;
            }
        }

        if (index == -999) return;

        if (value == null) {
            content.set(index, "");
        } else
            content.set(index, genLine(key, value));
    }

    private String genLine(String key, Object val) {
        return key.endsWith(":") ? key + " " + val : key + ": " + val;
    }
}
