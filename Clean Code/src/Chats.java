
import java.io.*;
import java.util.*;
import java.lang.reflect.Type;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;


public class Chats {
    private ArrayList<Message> list;
    private ArrayList<Message> result = new ArrayList<>();

    public ArrayList<Message> getList() {
        return list;
    }

    public ArrayList<Message> addMessage(Message temp) {
        for (Message item : list) {
            result.add(item);
        }
        result.add(temp);
        return result;
    }

    public void sortChat() {
        Collections.sort(result, new TimeCompare());
    }

    public ArrayList<Message> deleteMessage(String id) throws FileNotFoundException {
        FileOutputStream log = new FileOutputStream("logfile.log", true);
        PrintStream logger = new PrintStream(log);
        int count = 0;
        result.addAll(list.stream().collect(Collectors.toList()));
        for (Message item : result) {
            if (item.getId().equalsIgnoreCase(id)) {
                result.remove(item);
                count++;
            }
            logger.println("Count of deleted messages" + count);

        }
        return result;
    }

    public ArrayList<Message> searchAuthor(String author) throws NotFindChatException, FileNotFoundException {
        result.clear();
        FileOutputStream log = new FileOutputStream("logfile.log", true);
        PrintStream logger = new PrintStream(log);
        int count = 0;
        for (Message item : list) {
            if (item.getAuthor().equalsIgnoreCase(author)) {
                result.add(item);
                count++;

            }
        }
        if (!result.isEmpty()) {
            sortChat();
            logger.println("Count of alignments" + count);
            return result;
        } else {
            throw new NotFindChatException();
        }
    }

    public ArrayList<Message> searchWord(String word) throws NotFindChatException, FileNotFoundException {
        result.clear();
        FileOutputStream log = new FileOutputStream("logfile.log", true);
        PrintStream logger = new PrintStream(log);
        int count = 0;
        for (Message item : list) {
            StringTokenizer st = new StringTokenizer(item.getMessage(), " , .?!");
            while (st.hasMoreTokens()) {
                if (st.nextToken().equalsIgnoreCase(word)) {
                    result.add(item);
                    count++;
                }
            }

        }
        if (!result.isEmpty()) {
            sortChat();
            logger.println("Count of alignments" + count);
            return result;
        } else {
            throw new NotFindChatException();
        }

    }

    public ArrayList<Message> findTimeMessage(long firstTime, long lastTime) throws NotFindChatException, FileNotFoundException {
        result.clear();
        FileOutputStream log = new FileOutputStream("logfile.log", true);
        PrintStream logger = new PrintStream(log);
        int count = 0;
        for (Message item : list) {
            if (item.getTimestamp() >= firstTime && item.getTimestamp() <= lastTime) {
                result.add(item);
                count++;

            }
        }
        if (!result.isEmpty()) {
            sortChat();
            logger.println("Count of alignments" + count);
            return result;
        } else {
            throw new NotFindChatException();
        }
    }

    public void readFile(String fileName) throws FileNotFoundException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        Type collectionType = new TypeToken<ArrayList<Message>>() {
        }.getType();
        Gson gson = new Gson();
        list = gson.fromJson(reader, collectionType);

    }

    public void writeFile(String fileName) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);
        PrintStream ps = new PrintStream(fos);
        if (result.size() != 0) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            ps.println(gson.toJson(result));
        }


    }

    public ArrayList<Message> findRegular(String regular) throws NotFindChatException, FileNotFoundException {
        result.clear();
        Pattern pattern = Pattern.compile(regular);
        FileOutputStream log = new FileOutputStream("logfile.log", true);
        PrintStream logger = new PrintStream(log);
        int count = 0;
        for (Message item : list) {
            Matcher matcher = pattern.matcher(item.getMessage());
            if (matcher.find()) {
                result.add(item);
                logger.println("Count of alignments" + count);
            }
        }
        if (!result.isEmpty()) {
            sortChat();

            return result;
        } else {
            throw new NotFindChatException();
        }
    }

}
