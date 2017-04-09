package FileSystem;

import java.io.*;
import java.util.Vector;

import Message.Message;

public class Files extends FileHash {

    private Vector<Chunk> chunks = new Vector<Chunk>();
    private static final String    HEXES    = "0123456789ABCDEF";


    public Files(String fileName)throws UnsupportedEncodingException{
        super(fileName);
    }

    public Vector<Chunk> getChunks() {
        return chunks;
    }

    public void readFile() {
        ;

        try {
                FileInputStream is = new FileInputStream(name);
                fileSize = is.available();
                int fileSize = is.available();

            int byteSize;

            for (int i = 0; fileSize > 0; i++){
                if (fileSize <= Message.CHUNK_MAX_SIZE)
                    byteSize = fileSize;
                else
                    byteSize = Message.CHUNK_MAX_SIZE;

                Chunk chunk = new Chunk(i, byteSize);
                chunks.add(chunk);

                is.read(chunk.getData());
                fileSize = is.available();
                }
                    is.close();


            } catch(FileNotFoundException fnfE){
                fnfE.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }

        }

        public void load() {

            try (
                FileInputStream is = new FileInputStream("ChunkFolder/" + getHex(ID) + ".txt")){
                byte[] b = new byte[1];
                int nlines = 0;

                while (is.read(b) != -1)
                    if (getHex(b).equals("0a"))
                        break;

                while (is.read(b) != -1)
                    if (getHex(b).equals("0a"))
                        nlines++;

                fileSize = 0;

                for (int i = 0; i < nlines; i++) {
                    try {
                        try (
                                FileInputStream is2 = new FileInputStream("ChunkFolder" + getHex(ID) + "." + i)) {
                            byte[] chunk = new byte[is.available()];
                            fileSize += is2.available();
                            is2.read(chunk);
                            chunks.add(new Chunk(i, chunk));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();

            }
        }

        public void save(){
        //os for file information os2 for file data
            try {
                FileOutputStream os = new FileOutputStream("ChunkFolder/" + getHex(ID) + ".txt");
                os.write((name + '\n').getBytes());

                for (int i = 0; i<chunks.size();i++){
                    FileOutputStream os2 = new FileOutputStream("ChunkFolder/" + getHex(ID) + "." + i);
                    os2.write(chunks.get(i).getData());

                    os2.write((getHex(ID) + "." + i + "\n").getBytes());

                    os2.close();
                }
                os.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }

        public void join() {
            try{
                FileOutputStream os = new FileOutputStream("ShareFolder/" + name);
                for (int i = 0; i<chunks.size();i++)
                    os.write(chunks.get(i).getData());
                os.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }


    static String getHex(byte[] raw) {
        final StringBuilder hex = new StringBuilder(2 * raw.length);
        for (final byte b : raw) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }
}