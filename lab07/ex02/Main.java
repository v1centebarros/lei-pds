package lab07.ex02;

import java.text.Normalizer;

public class Main {

    public static void main(String[] args) {

        TextReaderInterface reader;
        String filename = "src/lab07/ex02/txt2.txt";

        //--------------TEXTREADER-----------
        System.out.println("---------------------TESTES Individuais----------------------------");
        System.out.println("---------------------TextReader----------------------------");
        TextReader textReader = new TextReader(filename);
        System.out.println("Paragrafo 1 : " + textReader.next());
        System.out.println("Paragrafo 2 : " + textReader.next());
        System.out.println("Paragrafo 3 : " + textReader.next()); //nao tem 3 paragrafo entao da null

        System.out.println("\n-----------------------NormalizerReader--------------------------");
        NormalizationFilter NormalizerReader =  new NormalizationFilter (new TextReader(filename));
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());

        System.out.println("\n---------------------VowelFilter----------------------------");
        VowelFilter VowelReader = new VowelFilter(new TextReader(filename));
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());
        System.out.println(NormalizerReader.next());

        System.out.println("\n---------------------TermFilter----------------------------");
        TermFilter TermReader = new TermFilter(new TextReader(filename));
        System.out.println(TermReader.next());
        System.out.println(TermReader.next());
        System.out.println(TermReader.next());
        System.out.println(TermReader.next());
        System.out.println(TermReader.next());

        System.out.println("\n------------------------CapitalizationFilter-------------------------");
        CapitalizationFilter CapitatizationReader = new CapitalizationFilter(new TextReader(filename));
        System.out.println(CapitatizationReader.next());
        System.out.println(CapitatizationReader.next());
        System.out.println(CapitatizationReader.next());
        System.out.println(CapitatizationReader.next());

        System.out.println("\n------------------------TerminouTesteIndividuais-------------------------\n");
        System.out.println("------------------------Outros Testes-------------------------");
        System.out.println("------------------------Capitalization-Vowel Testes-------------------------");
        CapitalizationFilter Reader1 = new CapitalizationFilter(new VowelFilter(new TextReader(filename)));
        System.out.println(Reader1.next());
        System.out.println(Reader1.next());
        System.out.println(Reader1.next());
        System.out.println(Reader1.next());
        System.out.println(Reader1.next());
        System.out.println("\n------------------------Normalization-Term-Capitalization-Vowel Testes-------------------------");
        NormalizationFilter Reader2 = new NormalizationFilter(new TermFilter(new CapitalizationFilter(new VowelFilter(new TextReader(filename)))));
        System.out.println(Reader2.next());
        System.out.println(Reader2.next());
        System.out.println(Reader2.next());
        System.out.println(Reader2.next());
        System.out.println(Reader2.next());

        System.out.println("\n------------------------Term-Normalization--Capitalization Testes-------------------------");
        //Ordem de execução Capitalization -> Normalization -> Term logo Químicos não tem a última letra maiúscula por causa de uma vírgula
        TermFilter Reader3 =new TermFilter( new NormalizationFilter(new CapitalizationFilter(new TextReader(filename))));
        System.out.println(Reader3.next());
        System.out.println(Reader3.next());
        System.out.println(Reader3.next());
        System.out.println(Reader3.next());
        System.out.println(Reader3.next());
        System.out.println("------------------------Terminou-------------------------");





    }

}