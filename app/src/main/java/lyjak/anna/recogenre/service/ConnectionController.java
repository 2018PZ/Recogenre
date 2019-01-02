package lyjak.anna.recogenre.service;

import android.os.Environment;
import android.util.Log;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class ConnectionController {

    private static final String TAG = ConnectionController.class.getName();

    private static final Credentials cred = Credentials.getInstance();
    private static final String dirProject = "recoGenre-server";
    private static final String dirMusic = "music";
    private static final String projectPath = "/home/student/" + dirProject;

    /**
     * Method creates connection to server and sends music file to it.
     * @param fileToSendPath - full path to sending file
     * @param fileName - name of sending file
     */
    public void executeRemoteConnection(String fileToSendPath, String fileName) {
        int port = Integer.parseInt(cred.PORT);
        try{
            JSch jsch = new JSch();
            Session session = jsch.getSession(cred.USER, cred.ADDRESS, port);
            session.setPassword(cred.PASSWORD);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            Log.i(TAG, "Session connected!");
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftpChannel = (ChannelSftp) channel;

            String currentDir = getActualPath(sftpChannel);
            Boolean result = createDirIfNotExist(currentDir, sftpChannel);

            if (result) {
                sftpChannel.cd(currentDir + "/" + dirMusic);
                currentDir = getActualPath(sftpChannel);
                Log.i(TAG, "Cd -> " + currentDir);
                sendFile(sftpChannel, fileToSendPath, fileName, currentDir);
                createAndSendTxtFile(sftpChannel, fileName);
                //TODO implement "python3 " + dirProject + "/quick_test.py"
                //TODO download result.txt
                //TODO display result
            }

            sftpChannel.exit();
            channel.disconnect();

//            Log.i(TAG, "First channel disconnect!");
//            ChannelExec channelExec = (ChannelExec)
//                    session.openChannel("exec");
////            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
////            channelExec.setOutputStream(baos);
////            channelExec.setCommand("python3 " + dirProject + "/quick_test.py | sed -n 5p | xargs echo -n");
//            channelExec.setCommand("ls | sed -n 5p | xargs echo -n");
//            channelExec.connect();
//            Log.i(TAG, "Second channel connected!");
////            channelExec.start();
////            Log.i(TAG, "Second channel started!");
////            channelExec.run();
////            Log.i(TAG, "Second channel runned!");
//            Thread.sleep(3000);
//            Log.i(TAG, "Sleep end!");
////            try{Thread.sleep(1000);}catch(Exception ee){}
//            final ByteArrayOutputStream baos = (ByteArrayOutputStream) channelExec.getOutputStream();
//            String aaaa = new String(baos.toByteArray());
//            Log.i(TAG, "Byte: " + aaaa);
////            byte[] tmp=new byte[1024];
////            while(true){
////                while(in.available()>0){
////                    int i=in.read(tmp, 0, 1024);
////                    if(i<0)break;
////                    System.out.print(new String(tmp, 0, i));
////                }
////                if(channel.isClosed()){
////                    if(in.available()>0) continue;
////                    System.out.println("exit-status: "+channel.getExitStatus());
////                    break;
////                }
////                try{Thread.sleep(1000);}catch(Exception ee){}
////            }
//
//            channelExec.disconnect();
//
//            Log.i(TAG, "Second channel disconnect!");

            session.disconnect();
            Log.i(TAG, "Session disconnect!");
        }
        catch(Exception e){
            Log.e(TAG, "Ups! Sth went wrong!");
            Log.e(TAG, e.getMessage());
        }
    }

    /**
     * Method checks actual server path (linux's pwd)
     * @param sftpChannel - SFTP Channel
     * @return path
     * @throws SftpException - SFTP method exception
     */
    private String getActualPath(ChannelSftp sftpChannel) throws SftpException {
        return sftpChannel.pwd();
    }

    /**
     * Method creates directory musicFiles on server if not exists
     * @param currentDirectory - actual directory
     * @param sftpChannel  - SFTP Channel
     * @return if dir exists
     * @throws SftpException - SFTP method exception
     */
    private Boolean createDirIfNotExist(String currentDirectory, ChannelSftp sftpChannel)
            throws SftpException {
        SftpATTRS attrs;
        try {
            attrs = sftpChannel.stat(currentDirectory+"/"+dirMusic);
        } catch (Exception e) {
            Log.e(TAG, currentDirectory+"/"+dirMusic+" not found");
            return false;
        }

        if (attrs != null) {
            Log.i(TAG, "Directory exists IsDir="+attrs.isDir());
        } else {
            Log.i(TAG, "Creating dir "+dirMusic);
            sftpChannel.mkdir(currentDirectory + "/" + dirMusic);
        }
        return true;
    }

    /**
     * Method sends file (name fileName) using channel (sftpChannel) form fileToSendPath to server's
     * folder (currentDir)
     * @param sftpChannel - SFTP Channel
     * @param fileToSendPath - path to file in mobile
     * @param fileName - file name
     * @param currentDir - current directory in server
     * @throws FileNotFoundException - throws if file doesn't found on mobile
     * @throws SftpException - SFTP method exception
     */
    private void sendFile(ChannelSftp sftpChannel, String fileToSendPath, String fileName,
                          String currentDir) throws FileNotFoundException, SftpException {
        File file = new File(fileToSendPath);
        FileInputStream stream = new FileInputStream(file);

        Log.i(TAG, "Start sending file");
        sftpChannel.put(stream, currentDir + "/" + fileName);
        Log.i(TAG, "File send");
    }

    /**
     * This method creates temporary file on phone 'RecoGenre' directory, to store actual file
     * to classification, next this file is send to server.
     * @param sftpChannel - SFTP Channel
     * @param fileName - name of file to classify
     * @throws SftpException - SFTP method exception
     */
    private void createAndSendTxtFile(ChannelSftp sftpChannel, String fileName)
            throws SftpException {
        Log.i(TAG, "Start creating temp file");
        try {
            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RecoGenre";
            File fileDir = new File(path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            File file = new File(path, "temp.txt");
            FileWriter textFileWriter = new FileWriter(file, false);
            String contentString = dirMusic + "/" + fileName;
            textFileWriter.write(contentString);
            textFileWriter.flush();
            textFileWriter.close();
            Log.i(TAG, "Content added");

            FileInputStream stream = new FileInputStream(file);
            sftpChannel.put(stream, projectPath + "/list_example.txt");
            Log.i(TAG, "Temp file sent");
        } catch (IOException e) {
            Log.e(TAG, "File write failed: " + e.toString());
        }
    }

}
