/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gerenciarbanco;
import java.util.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.PrintStream;  
import javax.swing.plaf.synth.Region;
import javax.swing.text.Document;
import javax.xml.stream.events.EndDocument;
/**
 *
 * @author CALIXTO
 */
public class GerenciarBanco{
    public static Scanner valor = new Scanner(System.in, "UTF-8");
    public static PrintStream out = new PrintStream(System.out, true, UTF_8);

    public static void main(String[] args) {
        
       int opcao;
        do{            
            opcao = Operacoes.MenuOperacoes();
            if (!Cliente.get_UserLogado()){   
                //USUARIO NÃO LOGADO CAI NO MENU PRINCIPAL
                switch(opcao){
                    case 1: // Entrar
                        // <editor-fold defaultstate="collapsed" desc="Entrar na conta">
                        out.println("====== Calixto Bank ======");
                        out.println("= Vamos Entrar na conta   =");
                        out.println("==========================");                    
                        
                        out.print("Digite sua conta: ");
                        String lcContaCorrente = valor.nextLine();
                        out.print("Digite a senha da conta: ");
                        String lcSenhaConta = valor.nextLine();
                        
                        if(lcSenhaConta.equals(Cliente.get_cSenhaCliente()) && 
                                lcContaCorrente.equals(Cliente.get_nContaCliente())){
                            Cliente.set_UserLogado(true);
                        }else
                        {
                            out.println("Usuario ou senha invalido!"); 
                        }                            
                        break;    
                        // </editor-fold>
                    case 2: // Abrir conta
                        // <editor-fold defaultstate="collapsed" desc="Abrir conta">
                        String cTentativa = "s";
                        do {
                            out.println("====== Calixto Bank ======");
                            out.println("=  Vamos abrir a conta   =");
                            out.println("==========================");                    
                            out.print("Nome Completo: ");
                            String lcNomeCompleto = valor.nextLine();

                            out.print("CPF: ");
                            String lcCPF = valor.nextLine();
                            
                            out.print("Senha para conta: ");
                            String lcSenha = valor.nextLine();

                            String cRetorno = Cliente.AbrirConta(lcNomeCompleto, lcCPF, lcSenha);                    
                            out.println(cRetorno);

                            if (cTentativa.contains("s") && cRetorno.contains("Atencao!")){
                                out.print("Deseja Tentar novamente? (S/N):");
                                cTentativa = valor.nextLine();                            
                            }else{
                                cTentativa = "n";
                            }

                        }while(cTentativa.contains("s"));
                        break;
                        // </editor-fold>
                    case 0: // sair                      
                        // <editor-fold defaultstate="collapsed" desc="Sair">
                        Cliente.DeslogarConta();
                        break;
                        // </editor-fold>
                    default:
                        // <editor-fold defaultstate="collapsed" desc="Opçao não encontrada">
                        out.println("Operação não encontrada!");    
                        break;
                        // </editor-fold>
                };
            }else
            {
                //USUARIO JÁ ESTÁ LOGADO                
                switch(opcao){
                    case 4: // sacar
                       // <editor-fold defaultstate="collapsed" desc="Sacar dinheiro">
                        out.println("Qual valor deseja Sacar?:"); 
                        double ValorSaq = valor.nextDouble();
                        Operacoes.SacarDinheiro(ValorSaq);                                            
                        break; 
                        // </editor-fold>
                    case 5: // depositar
                        // <editor-fold defaultstate="collapsed" desc="Depositar dinheiro">
                        out.println("Qual valor deseja depositar?:"); 
                        double ValorDep = valor.nextDouble();
                        Operacoes.DepositarDinheiro(ValorDep);
                        break;
                        // </editor-fold>
                    case 6: // saldo                            
                        // <editor-fold defaultstate="collapsed" desc="Saldo conta">
                        Operacoes.ConsultarSaldo();
                        break;
                        // </editor-fold>                        
                    case 7: // Encerrar conta
                        // <editor-fold defaultstate="collapsed" desc="Fechar conta">   
                        Operacoes.EncerrarConta();
                        break;
                        // </editor-fold>
                    case 0: // sair  
                        // <editor-fold defaultstate="collapsed" desc="Sair">
                        Cliente.DeslogarConta();
                        break;
                        // </editor-fold>
                    default:
                        // <editor-fold defaultstate="collapsed" desc="Opção nao encontrada">
                        out.println("Operação não encontrada!");    
                        break;
                        // </editor-fold>
                };
            }
        }while (opcao != 0);
        
        System.exit(0);
    }
    public static class Cliente extends Operacoes{
        public static String _NomeCliente;
        public static String get_NomeCliente() { return _NomeCliente; }
        public static void set_NomeCliente(String valor) { _NomeCliente = valor; }
        
        public static String _CPFCliente;
        public static String get_CPFCliente() { return _CPFCliente; }
        public static void set_CPFCliente(String valor) { _CPFCliente = valor; }
        
        private static boolean _UserLogado;
        public static boolean get_UserLogado() { return _UserLogado; }
        public static void set_UserLogado(boolean valor) { _UserLogado = valor; }
        
        private static double _Saldo;
        public static double get_Saldo() { return _Saldo; }
        public static void set_Saldo(double valor) { _Saldo = valor; }
        
        public static String _cSenhaCliente;
        public static String get_cSenhaCliente() { return _cSenhaCliente; }
        public static void set_cSenhaCliente(String valor) { _cSenhaCliente = valor; }
        
        public static String _nContaCliente;
        public static String get_nContaCliente() { return _nContaCliente; }
        public static void set_nContaCliente(String valor) { _nContaCliente = valor; }
        
        public Cliente(){
            set_NomeCliente("");             
            set_CPFCliente(""); 
            set_cSenhaCliente("");           
            set_UserLogado(false);
            set_Saldo(0.00);
        }
        
        private static void DeslogarConta() {
            set_UserLogado(false);            
                        
            out.println("");
            out.println("Obrigado, Volte sempre.");
            out.println("Finalizado!");
        }
    }
    public static class Operacoes{
        public static int MenuOperacoes() {
            Scanner valor = new Scanner(System.in);
            
            if (Cliente.get_UserLogado()){
                out.println("");
                out.println("====== Calixto Bank ======");
                out.println("    " + Cliente.get_NomeCliente()+"");
                out.println("   Saldo: R$ " + Cliente.get_Saldo() +"");
                out.println("=  Escolha uma operação  =");
                out.println("==========================");
                out.println("= 4 - Sacar dinheiro     =");
                out.println("= 5 - Depositar dinheiro =");
                out.println("= 6 - Saldo da conta     =");
                out.println("= 7 - Encerrar conta     =");
                out.println("= 0 - Sair               ="); 
                out.println("==========================");
            }else
            {
                out.println("");
                out.println("====== Calixto Bank ======");
                out.println("=  Escolha uma operação  =");                
                out.println("==========================");
                out.println("= 1 - Entrar na conta    =");                
                out.println("= 2 - Abrir nova conta   =");                
                out.println("= 0 - Sair               ="); 
                out.println("==========================");                
            }                
            out.print("Operação: ");
            int opcao = valor.nextInt();
            
            return opcao;
        }
        public static void ConsultarSaldo() {
            out.println("Seu saldo é: " + Cliente.get_Saldo());
        }
        public static void DepositarDinheiro(double valor) {            
            if(valor > 0.00){
                Cliente.set_Saldo(Cliente.get_Saldo() + valor);
                Operacoes.ConsultarSaldo();
            }else{
                out.println("Não é possivel depositar esse valor!");
            }            
        }
        public static void SacarDinheiro(double valor) {
            if(valor > 0.00 && valor <= Cliente.get_Saldo()){
                Cliente.set_Saldo(Cliente.get_Saldo() - valor);
                Operacoes.ConsultarSaldo();
            }else{
                out.println("Não é possivel Sacar esse valor!");
            }             
        }
        public static void EncerrarConta() {
            if(Cliente.get_Saldo() > 0){
                out.println("Não é possivel Encerar a conta, necessario sacar todo o saldo!");
            }else{
                Operacoes.LimparConta(Cliente.get_nContaCliente());
                out.println("Conta encerrada e dados zerados!");
            }                       
        }
        private static void LimparConta(String nConta){ 
            if (nConta.equals(Cliente.get_CPFCliente())){
                Cliente.set_nContaCliente("");
                Cliente.set_Saldo(0);
                Cliente.set_UserLogado(false);
                Cliente.set_cSenhaCliente("");
                Cliente.set_CPFCliente("");
                Cliente.set_NomeCliente("");
            }
            
        }
        public static String AbrirConta(String NomeCompleto, String CPF, String Senha) {
            String lcRetorno;
            if (!NomeCompleto.isEmpty() && !CPF.isEmpty() && !Senha.isEmpty()){
                Cliente.set_NomeCliente(NomeCompleto);
                Cliente.set_CPFCliente(CPF);
                Cliente.set_cSenhaCliente(Senha);
                
                //quando implementar em DB criar a conta unica para cada cleinte
                Cliente.set_nContaCliente("12345");
                
                lcRetorno = "Conta aberta, sua conta é: " + Cliente.get_nContaCliente();
            }else{
                lcRetorno = "Atencao! - Nome e CPF Obrigatorios!";
            }   
            return lcRetorno;
        }
    }
}

