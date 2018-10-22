package com.example.ljh.vr.login;

public class LoginBean {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class LoginResponse{
        private String username;
        private boolean status;
        private int responseCode;
        private String userRoot;
        private int id;

        public int getResponseCode() {
            return responseCode;
        }

        public void setResponseCode(int responseCode) {
            this.responseCode = responseCode;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUserRoot() {
            return userRoot;
        }

        public void setUserRoot(String userRoot) {
            this.userRoot = userRoot;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

    }

    public static class LoginCheckBox{
        public boolean isRemember() {
            return remember;
        }

        public void setRemember(boolean remember) {
            this.remember = remember;
        }

        public boolean isAuto() {
            return auto;
        }

        public void setAuto(boolean auto) {
            this.auto = auto;
        }

        private boolean remember;
        private boolean auto;
    }
}
