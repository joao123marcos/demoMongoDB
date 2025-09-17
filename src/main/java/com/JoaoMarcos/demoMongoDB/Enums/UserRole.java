package com.JoaoMarcos.demoMongoDB.Enums;

public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String userRole;

    //Esse é o construtor de um enum, repare que não tem nenhum modificador de 
    //acesso
    UserRole(String role){
        this.userRole = role;
    }

    //Nos getters e setters já tem modificador de acesso
    public String getRole(){
        return this.userRole;
    }
}
