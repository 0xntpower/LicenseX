package dev.licensex.server.utils.crypto;

import lombok.experimental.UtilityClass;

import javax.net.ssl.*;
import java.io.File;
import java.security.KeyStore;

@UtilityClass
public class SSLUtil {

    public static SSLServerSocket getSSLServerSocket(int port) throws Exception {
        SSLServerSocket sslserversocket = (SSLServerSocket) getServerFactory().createServerSocket(port);

        // we demand the client to authenticate
        sslserversocket.setNeedClientAuth(true);

        // defining what TLS versions we allow the server to use
        sslserversocket.setEnabledProtocols(new String[]{"TLSv1.3", "TLSv1.2"});

        return sslserversocket;
    }

    /**
     * Creates a server socket factory that uses the server's certificate
     * and whitelists the client one from the resources folder
     * @return a custom SSLServerSocketFactory
     */
    public static SSLServerSocketFactory getServerFactory() throws Exception {
        // Get the server's keystore
        String serverCertPassword = "HBTHDgsDSN3uwjFr5";
        File serverKeystoreFile = new File(ClassLoader.getSystemClassLoader().getResource("serverCertificate.jks").toURI());
        KeyStore serverKeyStore = KeyStore.getInstance(serverKeystoreFile, serverCertPassword.toCharArray());

        // a testing certificates to see how it reacts when we use a fake certificate
//        String serverTestCertPassword = "iFuxZ7a8xChTbGCLK";
//        File serverTestKeystoreFile = new File(ClassLoader.getSystemClassLoader().getResource("serverCertificate2.jks").toURI());
//        KeyStore serverTestKeyStore = KeyStore.getInstance(serverTestKeystoreFile, serverTestCertPassword.toCharArray());

        // Get the client's keystore
        String clientCertPassword = "fJpo3hC5N7DntUnv3";
        File clientKeystoreFile = new File(ClassLoader.getSystemClassLoader().getResource("clientCertificate.jks").toURI());
        KeyStore clientKeyStore = KeyStore.getInstance(clientKeystoreFile, clientCertPassword.toCharArray());

        // Whitelist the client's certificate on the generated servers
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("PKIX", "SunJSSE");
        trustManagerFactory.init(clientKeyStore);
        X509TrustManager x509TrustManager = null;
        for (TrustManager trustManager : trustManagerFactory.getTrustManagers()) {
            if (trustManager instanceof X509TrustManager) {
                x509TrustManager = (X509TrustManager) trustManager;
                break;
            }
        }

        // If the whitelisting gone wrong, throw an exception
        if (x509TrustManager == null) throw new NullPointerException();

        // Setup the server's keystore
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
        keyManagerFactory.init(serverKeyStore, serverCertPassword.toCharArray());
        //keyManagerFactory.init(serverTestKeyStore, serverTestCertPassword.toCharArray());
        X509KeyManager x509KeyManager = null;
        for (KeyManager keyManager : keyManagerFactory.getKeyManagers()) {
            if (keyManager instanceof X509KeyManager) {
                x509KeyManager = (X509KeyManager) keyManager;
                break;
            }
        }

        // If setting up the server's keystore gone wrong, throw an exception
        if (x509KeyManager == null) throw new NullPointerException();

        // set up the SSL Context
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{x509TrustManager}, null);

        return sslContext.getServerSocketFactory();
    }
}
