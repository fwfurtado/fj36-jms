package br.com.caelum.chat;

public class Client {
    public static void main(String[] args) throws Exception {
        User fer = new User("fernando.furtado@caelum.com.br", "Fer");
        User teteu = new User("matheus.brandino@caelum.com.br", "Teteu");

        ChatSession sessionA = new ChatSession(fer);
        ChatSession sessionB = new ChatSession(teteu);

        sessionA.send("Eai?");
        sessionB.send("Aqui nada!");
        sessionA.send("Ta fazendo o que?");
        sessionB.send("Porra nenhuma?");
    }
}
