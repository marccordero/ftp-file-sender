package mian;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;

public class ftp {
    public static void main(String[] args) throws IOException {
        String remoteWorkingDirPath = "/Pruebas_Java";
        String localFilePath = "prueba.txt";
        FileInputStream fis = new FileInputStream(localFilePath);
        String remoteFilename = "marcordero.txt";
        FTPClient client = new FTPClient();

        try {
            client.setBufferSize(512); // Opcional para definir Buffer size en bytes
            client.connect("********", 21); // no el puerto es por defecto
            client.login("******", "********");
            client.enterLocalPassiveMode(); // IMPORTANTE!!!!
            client.setFileType(FTP.BINARY_FILE_TYPE);
            client.changeWorkingDirectory(remoteWorkingDirPath);

            boolean uploadFile = client.storeFile(remoteFilename, fis);

            if (!uploadFile) {
                throw new Exception("Error al subir el fichero");
            }
        } catch (Exception e) {
            e.printStackTrace(); // Cambiar esto según tus necesidades de gestión de errores
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (client.isConnected()) {
                    client.logout();
                    client.disconnect();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
