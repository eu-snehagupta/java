/**
 * Write a description of ProcessGenes here.
 * @author (your name) @version (a version number or a date)
 */

import lang.stride.*;
import edu.duke.*;
import java.io.File;

    public class ProcessGenes {
    
    public int findStopCodon(String dna,int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon,startIndex+3);
        while (currIndex!= -1){
            if ((currIndex- startIndex)%3 == 0){
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon,currIndex);
            }
        }
        return -1;
    }
    
    public String findGene(String dna,int where){
        int startIndex = dna.indexOf("ATG", where);
        if(startIndex == -1){
            return "";
        }
        int taaIndex = findStopCodon(dna,startIndex,"TAA");
        int tagIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaIndex = findStopCodon(dna,startIndex,"TGA");
        int minIndex = 0;
        if (taaIndex== -1 || (tgaIndex!= -1 && tgaIndex <taaIndex)){
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }
        
        if (minIndex == -1 || (tagIndex != -1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }
        
        if (minIndex == -1){
            return "";
        }
        return dna.substring(startIndex,minIndex+3);
    }
    
    public StorageResource getAllGenes(String dna){
        StorageResource genList = new StorageResource();
        int startIndex = 0;
        while(true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()){
                break;
            }
            genList.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
        return genList;
    }
    
    public void count(String sr)
    {
        if (sr.length() > 9) {
            String subStringOfsr = sr.substring(10, sr.length());
            System.out.println("All the strings in sr that are longer than 9 characters: " + subStringOfsr);
            System.out.println("The number of strings in sr that are longer than 9 characters: " + subStringOfsr.length());
        }
    }

    public int howMany(String stringa, String stringb)
    {
        int count = 0;
        int fromIndex = 0;
        while (stringb.indexOf(stringa, fromIndex) != -1) {
            count = count + 1;
            fromIndex = stringb.indexOf(stringa, fromIndex) + stringa.length();
        }
        return count;
    }

    public void cgRatio(String sr)
    {
        float cgRatio= (float)(sr.length() / (howMany("C", sr) + howMany("G", sr)));
        int count= 0;
        if(cgRatio> 0.35){
            System.out.println("The Strings in sr whose C-G-ratio is higher than 0.35: " +sr);
            count ++;
        }
        System.out.println("The number of strings in sr whose C-G-ratio is higher than 0.35: " +count);
    }
    
    public void longestGene(String sr)
    {
        String temp= "";
        int length= 0;
        if(length< sr.length()){
            temp = sr;
            length = sr.length();
        }
        System.out.println("The length of the longest gene in sr: " +length);
    }
    
    public void testProcessGenes()
    {
        //FileResource fr =  new  FileResource();
        //String dna = fr.toString();
        //dna = dna.toUpperCase();
        String dna= "AACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTAACCCTCACCCTTCTAACTGGACTCTGACCCTGATTGTTGAGGGCTGCAAAGAGGAAGAATTTTATTTACCGTCGCTGTGGCCCCGAGTTGTCCCAAAGCGAGGTAATGCCCGCAAGGTCTGTGCTGATCAGGACGCAGCTCTGCCTTCGGGGTGCCCCTGGACTGCCCGCCCGCCCGGGTCTGTGCTGAGGAGAACGCTGCTCCGCCTCCGCGGTACTCCGGACATATGTGCAGAGAAGAACGCAGCTGCGCCCTCGCCATGCTCTGCGAGTCTCTGCTGATGAGAACACAGCTTCACTTTCGCAAAGGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCAGCGCCGGCGCAGGCGCGGAGGGGCGCGCCCGAACCCGAACCCTAATGCCGTCATAAGAGCCCTAGGGAGACCTTAGGGAACAAGCATTAAACTGACACTCGATTCTGTAGCCGGCTCTGCCAAGAGACATGGCGTTGCGGTGATATGAGGGCAGGGGTCATGGAAGAAAGCCTTCTGGTTTTAGACCCACAGGAAGATCTGTGACGCGCTCTTGGGTAGAGCACACGTTGCTGGGCGTGCGCTTGAAAAGAGCCTAAGAAGAGGGGGCGTCTGGAAGGAACCGCAACGCCAAGGGAGGGTGTCCAGCCTTCCCGCTTCAACACCTGGACACATTCTGGAAAGTTTCCTAAGAAAGCCAGAAAAATAATTTAAAAAAAAATCCAGAGGCCAGACGGGCTAATGGGGCTTTACTGCGACTATCTGGCTTAATCCTCCAAACAACCTTGCCATACCAGCCCATCAGTCCTCTGAGACAGGTGAAGAACCTGAGGTCGCAGGAGGACACCCAGAAGGTCCAGAGAGAGCCTCCTAGGCCCCCCACCTCCCCCCGTGGCAGCTCCAACCCCAGCTTTTTCACTAGTAAGGCAGTCGGGCCCCTGGGCCACGCCCACTCCCCCAAGCGGGGAAGGAGCTTCGCGCTGCCGCTTGGCTGGGGACTGGGCACCGCCCTCCCGCGGCTCCTGAGCCGGCTGCCACCAGGGGGCGCGCCAGCGGTGTCCGGGAGCCTAGCGGCGCGTGTGCAGCGGCCAGTGCACCTGCTCTGGCCCTCGCCGCGGTCTCTGCCAGGACCCCGACGCCCAGCCTGACCCTGCCATTCAGCGGGGCTGCGGCTCCACGGCCTGCGACAGCAGCCCCACCTGGCATTCAGCGCGCTCCCGGGGGCAGAGGTCGCGGTGTCCTCACGCTGTGGTGCCGGCCTACAACCCCCACGCCGGGCTCGGGCCCGGCGGAGGAGGGCGATGCTCCCCGGGTAGGACAAACCGGTCACCTGGGCTGCGACGGCGGCTTAGGGGCAGAAGCGGCGGTCCAGGGCCGCCTGGCGCAGCAGCCTGTCCCAGCCGCGGTCCCTGCAGTCCCTCCCTGGCGGCTGCGCAGCCGTCCCACGACAGGGGCCATAAACTCTCCAGAGCGGAAAGCCGCACCCTGGTGGCCCGGCCCCGCGCCCAGACCTGGCGGCCGCTGGCACCTGACCCGCTGCATGGGTCTCCAGGGAGCTCGCTGCCCACCCGGCGCTGCAGGCTCGGCTCCCTCGTACACTCTCTGGTAGGTGCTAGGGACGACCCTATGGGCCAGCTTGCCATGCCCAGTCCCCAGGCCGCACCCACCCTGGCTCCCTGGGCTAGGGGACTGGCTCCTCCTGTGAGTCGTGGGTCTGGGAGGCAGGGGCGTTAGGGGAGAGTGAGGGACCGAGGGCAGCCCCTGCTGTGTGCACAGCGAGGTCGTGCACAGGCGTCTGTTGCAGAGCGTGCAGCTTCAGATGAGACTGGATTGCAGGTGGAGATGACTGTGGGTGCGCACACCTGGAGGTGAAGGGGAGGCAGCCTGTCTACCTGACCCATGAAATACAGGAGACTGTACCCCAGAAGCAGCGGGTTCACTGCTCCATTGATTAAGCAAGTCTGGGACACACATGTAGCTAAGCTGTGAGTTCTGTACCAGCGATCCCAACACCCACGCCCTCAGAAAGACACTGGTGTGGGGCCTGGGTGCTTGTCAGGCCTGAAAGTGGAGAGCACGGGCCAGAGACACTGAGTAGGGGGAACCCACCCTAGGGCTCTGAGGGACGACGATGTGGGGAGCTGGTGACAGAGCCTGAGCTGGCCCAATGTTGCACGGTGGGGACAGATTCGAGGTACAGTGGGGACTGGTGACCTCAGTTCCCAGTGTCCCAGCCTGGCCTCCCAGTCCACCCAGCAATTAGTGGGTGCTGCCCTGCAAAGACTCTGGGGGTGCCTCAGCCCTCCTCATCACACGTGACTGGTGACTTCTGTGTCCACCCGCACAATAAGAGGGATCTTCTCTCACTTTCAGGCAAGCCCAAGAAAGTCAGGGGCCTATGTGAGCCAAAGAGGAGAGAAGGTGATGCCTCAGCCCAGTGTTTCTGCCCCACCTCGCTTGTGGCCTTCGGAACTTGATTTGCACCGCAGGAAAATGGGCAATGAAAACCCCTCCCTAACTGGCTTCTCAGTCCACTCTGACCAGCCCACTGCACAGCGCCCACCCTGCAGCTCCAGGTACAGAGGCTGGGATGGCTCTGGGCTGACCTAAGGGCCTTCTGATGGCTCCAACCCTCGGGATGCCTCATGCTCACCCTTTGGCACCCACCTGACAGCTCAGCATCTCTGCTCTCTGCCATCCTCAATGCCTGCTCTAGACAAGCCCAAGTCCCCCAGGAGTGGCAGAGGGAACTGAGCCGAAAACTAAGTCTCGGCTCACTGAACCCCAAGTGGGCTGTCCAGCCTCGCCCTTCAGTTCACAACCCCAGGCAGGTTCCCTCCAGGGATGTGATCCCAGGGGCCACAGCAGCACATTCTGGCCTAACCTATCCACTATTTAAACAGTTACTGAAAAGGCCAGGATGGCCGTGGGCCCTGACATTAATCCCCTTTCTCTGTGAGGGGGCTGGGTTGGGTTTGCCATCCTGATGTCTTTGTGGAAAGAGCTGGCAGGTGAAGCAAGTCTCAGGGGCCAGCCATGGGACAAGGAACCTAGGACTGGCCTCTGCTGGAACCCTCTGAGGCCCCTGCGGACAGGAGGATCCAATGGAGGTCTAGCCACCCCTCCCAGGTTGGTGCTCACAGCCCCTCCCTGGCCCACTCCCTGCACACCTGCACCTGCTGGTCTCTGGGAGAGGAGCATCCATCCATCTTGTGCGCATAGCTTTCGGCTCCATTTTCATGAGGATGGTCTCCTTGGCAGAAATGCCCATTAGGGGATCCTGAGCCTGTGCTAGCTCTTCTCTAAGTGCCAAAGCCAGTGAGAGGGACTTGAAAACTCAAGACTTATTAACAGTATTTTCTGCATTTTGTGCTTTCAGGGTTGTTTTTTCCTTAAAATGTGTAAAAACAAACATTGAGATTTCTATCTTTTATATAATTTGGATTCTGTTATCACACGGACTTTTCCTGAAATTTATTTTTATGTATGTATATCAAACATTGAATTTCTGTTTTCTTCTTTACTGGAATTGTTAACTGTTTTATAGGCCAAATCTTTTAAAAAAAACACATCTCTCTAATTTCTCTAAACATTTCTAATTACATATATATTTACTATACCTAATACACTACTTTGGAATTCCTTGAGGCCTAAATGCATCGGGGTGCTCTGGTTTTGTTGTTGTTATTTCTGAATGACATTTACTTTGGTGCTCTTTATTTTGCGTATTTAAAACTATTAGATCGTGTGATTATATTTGACAGGTCTTAATTGACGCGCTGTTCAGCCCTTTGAGTTCGGTTGAGTTTTGGGTTGGAGAATTTTCTTCCACAAGGGATTGTCTTGGATTTTTCTGTTTCTCCCTCAATATCCACCTGGAAAACATTTCAATTAATTTATATTTACTTAAATATTTCTGTGCAAAAACTGTGTACAAAAGCCCCAAAGCATAATTTGTGCAGTTGAGCGCATGTTCTGTTGTTCAGCATTTATGGTGGTTGGTAGTGGAAAAGATTTTTAGAATATGTGGATTTTCGGGATATTCCCAGAAGCCCAGATAGCGACACTTTACCTTTGGAGGAATTACTTCTCAGAATATTGCACACAATCAATCGCCTTTGGAAGGAGCATATATCCCCAGCAAAAGCTCTGGTTTTTTGAAGTCTGTATTGTGTGTTACTTCCAGGAGAATATGCAATGATGACAATGTTATTAGATGATTCAAATATGAAGTGCTGTTATGCCAAACAATGAATCTTTGTGTTATACATTATGCCTAACTATAAATCTTTGTGTTATACATTTTAATGTCATTGGAGAGTACTCCTGTCTTCTTGGCATTATTGATAATTAGATTCTAATTGCTAATAAGTCAGAAAAATTAGGAACACCAAATTTCAGTTGTCTCAAAAGCACTCCTCTTATTAAATTTGGATGTTTACCTTTATCACATCAAAAGAAATATTGTTAGAAAGGTGTTTAATGTTTTGCAGATGGATAGATTACTGTTATTAGTTCTCATTTCATTGTTAATTTTTAAAACCATAAGGTTGGAAGTATCAATATGCCTTTCAATATACCTTAGTGGAATTTATTAAATTTTCATGGATGTCCTTTAGGGGGTTCAGGAAGTTATTTCTATTGCTAGATTTCTGGAAGATTTATCAGGAATGAGTGTCAGACATTGTCAGACGTCCATTGAAATCATCATGGTCTTTTCCTTTATTCTATTAATATGATGTATTACACTGATTGATTTTTAAATTTGTATTGGTAGGATAATTCCACTTGGTTATATTGTCTAACTTTTTTCTAATTTTCTTTCATTTTTATTACAGATGAGGCCTCACTCTGTCACCCAGGTTGGGGTGGAGTGGCACAGTCACAGCTCACTATAACCTCAAGCTCCTGGGCTCAAGTGATCCTGCCACCTCAGCCTCCTAAGTAGCTGGAACTACAGATGTGCACTGCCATGCCAGGCTTGTCTAACATTTTTATGTGTTGCTTCATCCAGTTTGCTAGAGTTTTTGGAGATTTCTGTCTTCATTCATGAGGGATAATAGTCTGCACTTTTATTTTCTTGTGATACTTTTGTCTGATTTGTTATCTGGGTAATACTGGCCTTGAAAATGAATTGATGTTTTCCTGCTTCTCTGCTTTGCAAGTGTTTGTGAAGGATTGGTTATTCATTAAGTGTTTAATAGAATTCACTAGTGAAGCTATGTGAGCCAGGGCTAGACTGATGAAGAGTTTTCATTAGTCTAATCTGTTTACTTGCTGTATAAGTACGCATATATTCTCTTTCTTCTTGATTTAATTTTACACTTTGTGTATAGCAGGGAATCTGTGTCTAATTTGTAGTATTTCATGCTTCTAGGTTTTCATGGCAGTTGAGATGTAAGAATAACAATAATGTTGGGAGAAGGAAGTTGTGGACAATCCATGAATATCCCAACATCTGTTGTAGGAAGGTTAAGATTACTTTTTTTTTTTTTGCTGTACTGAACTGAATACTCTTATTTATAATGTCAGACAAATGTAATGTTGTATATAAATAGAACTAGGAAAATGTGCCATTTGTCTTAGTATTTAATCAAGATGGAAGTCTGGGCCTACCTCCTCTCTTTTATTAATATGTAGACAGGACACCAACACAAATTAGAATGAAGACAAACAAAATGTTAGCAAATGAAGAATGGTATCAATTGGTTAAAATGTGATGAAATAGAGTGGTGAATATTTACATAGAATCCATGATGTGTTAGGTGCTATTTCAAGCTATTTGCACATATAGTTTTAATACCAATGACGTTAAAATGTATAACACAAAGATTCATATAAATAAAAATTACAACATTGTAAATAATATTAGGTGACACTAAAACTGTCATAGAAATACACATTTATATAAAACATAAAGTAACATGAAGTATTAAATTTTAGAAACTTTGATTACTAATCAGATGAACAACTGATTAGCCTTTTTATCCAGTAAAAAAGGCATACATATTATTTTCAAATTCCAGAGACAAATATTTTAAATATTGAAGTTGAAGACCTAAAAATGTGTCACTGACCTCATGGAAGTAGATATTCACTAGGTGATATTTTCTAGGCTCTCTGAAATTATATCAGAAAAATGTGAATTAGAATATAACCCATAAATAATATCTGGCCACATACAAAGTAATTGAAGATCAATTTAAATGGCTATTGGATTAAGAAATAGGGACTGAGGTAAATTTGCAGTGTCAGGGAGGATCTAAGGAGGAAGCATTGACACTGGAGCCCAAGGACCTGGGATCACAGAACAGATTCTACCAGTGCTAACTTACTGCTCCACAGAAAACATCAATTCTGCTCATGCGCAGGTACAATTCATCAAGAAAGGAATTACAACTTCAGAAATGTGTTCAAAATATATCCATACTTTGACATATTAATGAAGTAATCACATTCTACACATAACTACTCCATATGGAATACTGGGGAGGAGGTGTTCCAAATAAAGAGACTGAGGATTTCTCATGAGAACTCAGTGTCTGCTAGAAAATATCTAAGTAAAATATTTTACTTATGTGGAAAGTGTGGATGTTTGTGCATCAAAAGTTTCAAGAATCCCTAAAATTTACAATGGAGATGAGGAGAAAATATCAGAATTTCCCAGCACCAGAAATAAGGCAAGAAAAAATTCAGAGGGGTTGTAAATGTGAAAAGCCAATGGCTGGTCACACAGCAACATTGATAACCTTGTGCCTGGACAACTAGAATAAATACATAAACATACACATTGAAAATATTTCCAATATTAGATCTCCCTCATGTGAGAACTAAATTATAAAGATTGAAGCATAGAAGAAAATAAGCTACCAGAATAAATTTGATTACACATAAATTTCTGATATTGAAACTGTCACAAATGTTTAAGTTGGTAGTGGAAGACAAAGGACATATAATCTTGGGAGTCCTAAGGCCCTGCCCACTGCCAGTCCCTCCACACTACTACAGCTGATGCTTTCTGGAAATCACCACCTCCTGGCAGGAGCCCAACCAGCACAAATATAGAGCATTAAACCACCAAAGCTAAGGAGGCTCACAGAGTCTATTGCACCCTTCACCACCTCCACTGGAACAGGCGCTGGTATCCATGGCTCAGAGACCCAAAGATGGTTCACATCACAGGGCTCTATGCAGACAACCCCCAGTACCAGCCCAAAGCCACGTAGACCTGCTGGGTGGCTAGACCCAGAAGAGAGACAACAATCAATGCACTTTGGCTTACAGGAAGCCATGCCCATAGGAAAAAGGGGAGAGTACTACGTCAAGGGAACACCCCGTGGGATGAAAGAGTCTGAACAACAGTCTTCAGCCCTAGACCTTTCCTCTGACAGAGTCTACCAAAATGAGAAGGAACCAGAAAACCAACCCTGGTAATCTGACAAAACAAGAATCTTCAACACCCCCCAAAAAATCACACCAGTTCATCACCAATGGATCCAAACAAAGAAGAAATCACTGATTCATCTAAAAAAAAATTCAGGTTAGTTATTAAGCTAATCAGGGAGGGGCCAGAGAAAGATGAAGCCCAATGCAAGAAAATCCAAAAAATGATACAATACGTGAAGGGAGAATTATTCAAGGAAATAGATAGCTTAAATAAAAAAATAAAAAATCAGGAAACTTTGGACGTACTTTTAGAAATGTGAAATGCTCTGGAAAGTCTCAGCAATAGAATTGAACAAGTAGAAGAAAGAAATTCAGAATTCGAAGACAAGGTCTTTGATTTAACCCAATCCAATAAAGACAAAGAAAAAAGAATAAGAAAATATGAGCAAAGTCTCCAAGGAGTCTGGCATTCTGTTAAATGATGAAACCTAACACTAATTGGTGTACCTGAGGAAGAAGTGAATTCTAAAAGCCAGGAAAACATATTTGGGAGAATAATCTAGGAAAACTTCCATGGCCTTGTGAGAGACCTAGACATCCAAATACAAGAACCACAAATAACACCTGGGAAATTCATCACAAAAAGATCTTAGCCTAGGCACATTGTCATTAGGTTATCCAAAGTTAAGACAAAGGAAAGAATCTTAAGAGCTGTGAGACAGAAGCACTAGGTAACCTATAAAGGAAAACCTGTCAAATTAACAGCAGATTTCACAGCAGGAACCTTACAAGCTAGATGGGATTGGGGCCCTTTCTTCAGCCTCCTCAAACAAAACAATTATCAGCCAAGAATTTTGTATCCAGCAAAACTAAACATCATATATGAAGGAAAGATACAGTCATTTTCAGACAAACAAATGCTGACAGAATTTGCCATTACCAAGCCAGGACTCTAAGAACTGCTAAAAGGAGCTCTAAATCATGAAACAAATCCTGGAAACACATCAAAACAGAACTTCATTAACGCATAAATCACACAGGACCTATAAAACAAAAATACAAGTTAAAAAACAAAAACAAAGTACAGAGGCAACAAAGAGCATGATGAAAGCAATGGTACCTCACTTTTTAATACTAATGTTGGTTGTAAATGGCTTAAATGCTCCACTTACAAGATACAGAACCACAGAATGGATAACAACTCACCAACTAACTATCTGCTGCCTTCAGGAGACTCACCTAACACATAACGACTTACATAAACTTAAGGAAAGTGGTAGAAAAAGGCATTTCATGCAAATGGACACCAAAAGCAAGCAGCAGTAACTATTCTCATATGAGACAAAACAAACTTTAAAGCAACAGTAGCTAAAAGAGACAAAGAGAGACAGTATATCATCTGTCACCTGACAGTCTCATCCAACAGAAAAATATGACAATCCTAAACATATGTGAACCTAACACTGGAGCTCCCAAATTTATAAAACAATTACTAGTAGACATAAGAAATAAGATAGACAGCAACACAATAATAGTGGGGGACTTCAATACTCCACTGACAGCACTAGACAGGTCATCAAGACAGAAAGTCAACAAAGAAACAATGGATTTAAACTATACTTTGGAACAAATGGACTTAACAGATATATATAGAACATTTCATCCAACAACCACAGAATACACATTCTATTCAACAGCACATGGAATTTTCTCCAAGATAGACCATATGATAGGCCATAAAATGAGTCTCAATAAATTTAAGAAAATTGAAATTGTATCACGCACTCTCTCACATCACAATGGAATAAAACTGAAAATCAACTCCAAAAGGAATCTTCGAAACCATGCAAATACATGGAAATTAAATAACCTGCTCCTGAATGAGCATTGGGTGAAAAACGAAATCAAGATGGAAATGTAAAAAATTTCTTCGAACTGGATGACACAACCTATCAAGACCTCTGGGATACAGCAAAGGCAGTGCTAAGAGGAAAGTTTATAGCACTAAACACCTACGTCGAAAAGTCTGAAAGAGCACAGACAATCTAAGTTCACATCTCAGGGAACTAGAGAAGGAGGAACAAGCCAAACCCAATCCCAGCAAACAAAGGAAATAACCAAGATCAGAGCAGAACTAAATGAAATTGACACAACAACAACAACAACAAAAATACAAAACATAAATAAAACAAAAATTTGGTTATTTGAAAAGATA";
        StorageResource genesList = getAllGenes(dna);
        for (final String g : genesList.data()) {
            System.out.println("Our dnagenes: " + g);
            count(g);
            cgRatio(g);
            longestGene(g);
        }
    }
}