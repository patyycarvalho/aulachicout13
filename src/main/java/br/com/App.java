package br.com;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class App 
{
    public static void main(String[] args) {
        // Criando uma lista de produtos
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Produto 1", 10.50));
        produtos.add(new Produto("Produto 2", 20.75));
        produtos.add(new Produto("Produto 3", 15.00));

        // Serializando a lista de produtos
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("produtos.dat"))) {
            outputStream.writeObject(produtos);
            System.out.println("Lista de produtos serializada com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Desserializando a lista de produtos e exibindo no console
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("produtos.dat"))) {
            @SuppressWarnings("unchecked")
            List<Produto> produtosDesserializados = (List<Produto>) inputStream.readObject();

            System.out.println("Produtos:");
            for (Produto produto : produtosDesserializados) {
                System.out.println("Nome: " + produto.getNome() + ", Preço: " + produto.getPreco());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}