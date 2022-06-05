package dev.licensex.server.utils;

import lombok.experimental.UtilityClass;

import javax.net.ssl.*;
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
        // create a default keystore
        KeyStore serverKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        // load it with our certificate
        serverKeyStore.load(ClassLoader.getSystemClassLoader().getResourceAsStream("serverCertificate.jks"), serverCertPassword.toCharArray());

        // Get the whitelisted keystores
        X509TrustManager Managerx509TrustManager = getManagerXTrustManager();
        X509TrustManager Clientx509TrustManager = getClientXTrustManager();

        // Setup the server's keystore
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509", "SunJSSE");
        keyManagerFactory.init(serverKeyStore, serverCertPassword.toCharArray());
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
        sslContext.init(new KeyManager[]{x509KeyManager}, new TrustManager[]{Clientx509TrustManager, Managerx509TrustManager}, null);

        return sslContext.getServerSocketFactory();
    }

    private static X509TrustManager getClientXTrustManager() throws Exception {
        String clientCertPassword = "fJpo3hC5N7DntUnv3";
        KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        clientKeyStore.load(ClassLoader.getSystemClassLoader().getResourceAsStream("clientCertificate.jks"), clientCertPassword.toCharArray());

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

        if (x509TrustManager == null) throw new NullPointerException();

        return x509TrustManager;
    }

    // this does nothing for some reason?
    private static X509TrustManager getManagerXTrustManager() throws Exception {
        String clientCertPassword = "Zoy7CLfFc5UtG3hWP";
        KeyStore clientKeyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        clientKeyStore.load(ClassLoader.getSystemClassLoader().getResourceAsStream("managerCertificate.jks"), clientCertPassword.toCharArray());

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

        if (x509TrustManager == null) throw new NullPointerException();

        return x509TrustManager;
    }
}
