package com.finastra.vaoo.client.ffdc.auth.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SecretStorage {
    private Session session;
    private static volatile SecretStorage instance;

    public static SecretStorage getInstance() {
        SecretStorage localInstance = instance;
        if(localInstance == null){
            synchronized (SecretStorage.class){
                localInstance = instance;
                if (localInstance == null){
                    instance = new SecretStorage(new Session());
                }
            }
        }
        return instance;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
