package com.example.bancodedados;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            //CRIAR BANCO DE DADOS
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //CRIAR TABELA
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas (id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

            //bancoDados.execSQL("DROP TABLE pessoas");


            //INSERIR DADOS
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Mariana', 17) ");
            //bancoDados.execSQL("INSERT INTO pessoas(nome, idade) VALUES ('Mario', 35) ");

            //bancoDados.execSQL("DELETE FROM pessoas WHERE id = 4");


            //RECUPERAR PESSOAS
/*
            String consulta = "SELECT nome, idade FROM pessoas " +
                              "WHERE nome = 'Thiago' AND idade = 27";

            String consulta = "SELECT nome, idade FROM pessoas " +
                              "WHERE idade >= 27 OR idade = 18";

            String consulta = "SELECT nome, idade FROM pessoas " +
                              "WHERE nome IN(Thiago)";

            String consulta = "SELECT nome, idade FROM pessoas " +
                              "WHERE idade BETWEEN 20 AND 40 ";

            String consulta = "SELECT nome, idade FROM pessoas " +
                              "WHERE nome LIKE '%i%' ";*/

            String consulta = "SELECT * FROM pessoas " +
                              "WHERE 1=1";

            Cursor cursor = bancoDados.rawQuery(consulta, null);

            //INDICES TABELA

            int indiceID = cursor.getColumnIndex("id");
            int indiceNome = cursor.getColumnIndex("nome");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst(); //Volta o cursor para o primeiro item da lista

            //EXIBIR
            while (cursor != null){

                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);
                String id = cursor.getString(indiceID);

                Log.i("RESULTADO - id ",id+ "  /  nome "+ nome + "  /  idade: "+idade);

                cursor.moveToNext(); //Move o cursor para o próximo item
            }
        }catch (Exception e){
            e.printStackTrace();

        }

    }
}