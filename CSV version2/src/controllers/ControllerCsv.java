/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import models.ModelCsv;
import views.ViewsCsv;
/**
 *
 * @author fanny
 */
public class ControllerCsv {
    ModelCsv modelCsv;
   ViewsCsv viewCsv;
     ActionListener al = new ActionListener (){
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==viewCsv.jb_guardar){
                jb_guardar_action_performed();
            }
            else if(e.getSource()==viewCsv.jb_nuevo){
                System.out.println("e.getSource()==blocNotas.jb_nuevo");
                jb_nuevo_action_performed();
            }
            else if(e.getSource()==viewCsv.jb_primero){
                jb_primero_action_performed();
            }
             else if(e.getSource()==viewCsv.jb_anterior){
                jb_anterior_action_performed();
            }
              else if(e.getSource()==viewCsv.jb_siguiente){
                jb_siguiente_action_performed();
            }
             else if(e.getSource()==viewCsv.jb_ultimo){
                jb_ultimo_action_performed();
            }
        }
        

       
        private void jb_primero_action_performed() {
           this.primero(modelCsv.getPath());
        }
        private void jb_anterior_action_performed() {
          this.anterior(modelCsv.getPath());
        }

        private void jb_siguiente_action_performed() {
            this.siguiente(modelCsv.getPath());
        }

        private void jb_ultimo_action_performed() {
            this.ultimo(modelCsv.getPath());
        }

              public String primero(String path){
         try{  
             String acumulador ="";  
             String row;
             try (FileReader archivo = new FileReader(path)){              
                 BufferedReader bufferedReader = new BufferedReader(archivo);
                     while ((row=bufferedReader.readLine())!= null){
                        acumulador+=row+"&"; 
                     }
                     String datos[] =  acumulador.split("&");
                     modelCsv.setPosicion(0);
                     String registro[] = datos[modelCsv.getPosicion()].split(",");
                     viewCsv.jtf_nombre.setText(registro[0]);
                     viewCsv.jtf_email.setText(registro[1]);            
             } catch(FileNotFoundException err){
                 JOptionPane.showMessageDialog(null,"File not found"+err.getMessage());
             }        
         } catch(IOException err){
             JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
        } 
        return null;    
    }
         
            public String ultimo(String path){
        try{  
            String acumulador =""; //acomulara cada linea 
             String row; // row=fila
             try (FileReader archivo = new FileReader(path)){              
                 BufferedReader bufferedReader = new BufferedReader(archivo);
                 int contador=0;
                     while ((row=bufferedReader.readLine())!= null){
                         contador = contador + 1;
                        acumulador+=row+"&"; //en caso de que el archivo tenga mas de una linea 
                     }
                     String datos[] =  acumulador.split("&");
                     modelCsv.setPosicion(contador-1);
                     String registro[] = datos[modelCsv.getPosicion()].split(",");
                     viewCsv.jtf_nombre.setText(registro[0]);
                     viewCsv.jtf_email.setText(registro[1]); // posicion de registros             
             } catch(FileNotFoundException err){
                 JOptionPane.showMessageDialog(null,"File not found"+err.getMessage());
             }        
         } catch(IOException err){
             JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
         } 
         return null;   
           }
             public String siguiente(String path){
        try{  
            String acumulador =""; 
             String row;
             try (FileReader archivo = new FileReader(path)){              
                 BufferedReader bufferedReader = new BufferedReader(archivo);
                 int contador =0; 
                     while ((row=bufferedReader.readLine())!= null){
                        contador = contador + 1;
                        acumulador+=row+"&"; 
                     }
                     String datos[] =  acumulador.split("&"); 
                     modelCsv.setPosicion(modelCsv.getPosicion()+1); 
                     if (modelCsv.getPosicion() < contador){
                         String registro[] = datos[modelCsv.getPosicion()].split(","); 
                         viewCsv.jtf_nombre.setText(registro[0]);
                         viewCsv.jtf_email.setText(registro[1]); 
                     }  
                     else{
                         modelCsv.setPosicion(modelCsv.getPosicion()-1); 
                         JOptionPane.showMessageDialog(null,"Se encuentra en el ultimo registro ");
                     }
             } catch(FileNotFoundException err){
                 JOptionPane.showMessageDialog(null,"File not found"+err.getMessage());
             }        
         } catch(IOException err){
             JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
        } 
        return null;    
    }
               public String anterior(String path){
        try{  
            String acumulador =""; 
             String row; 
             try (FileReader archivo = new FileReader(path)){              
                 BufferedReader bufferedReader = new BufferedReader(archivo);
                 int contador =0; 
                     while ((row=bufferedReader.readLine())!= null){
                        contador = contador + 1;
                        acumulador+=row+"&"; 
                     }
                     String datos[] =  acumulador.split("&");
                     modelCsv.setPosicion(modelCsv.getPosicion()-1); 
                     if (modelCsv.getPosicion()>=0){
                         String registro[] = datos[modelCsv.getPosicion()].split(","); 
                         viewCsv.jtf_nombre.setText(registro[0]);
                         viewCsv.jtf_email.setText(registro[1]);  
                     }  
                     else{
                         modelCsv.setPosicion(modelCsv.getPosicion()+1); 
                         JOptionPane.showMessageDialog(null,"Se encuentra en el primer registro");
                     }
             } catch(FileNotFoundException err){
                 JOptionPane.showMessageDialog(null,"File not found"+err.getMessage());
             }        
         } catch(IOException err){
             JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
        }        
        return null;    
    }
               
     };
     public ControllerCsv(ModelCsv modelCsv, ViewsCsv viewCsv){
        viewCsv.setVisible(true);
        this.modelCsv = modelCsv;
        this.viewCsv = viewCsv;
        this.viewCsv.jb_nuevo.addActionListener(al);
        this.viewCsv.jb_guardar.addActionListener(al);
        this.viewCsv.jb_primero.addActionListener(al);
        this.viewCsv.jb_anterior.addActionListener(al);
        this.viewCsv.jb_siguiente.addActionListener(al);
        this.viewCsv.jb_ultimo.addActionListener(al);
        //this.primero(modelCsv.getPath());
      }
      
        private void jb_guardar_action_performed() {
            this.writeFile(modelCsv.getPath());
            
            
                    }//guardar

        private void jb_nuevo_action_performed() {
            viewCsv.jtf_nombre.setText(null);
            modelCsv.setNombre(null);
            viewCsv.jtf_email.setText(null);
            modelCsv.setEmail(null);
            
            
        }//nuevo
     
     
      public String writeFile (String path) {
        try{
            File  file = new File(path); //Ruta del arhivo que se abrira
            FileWriter fileWriter = new FileWriter(file,true);
            modelCsv.setNombre(viewCsv.jtf_nombre.getText());
            modelCsv.setEmail(viewCsv.jtf_email.getText());
            modelCsv.setResultado(modelCsv.getNombre() + "," + modelCsv.getEmail());
            try(PrintWriter printWriter = new PrintWriter(fileWriter)){               
                printWriter.println(modelCsv.getResultado());
                printWriter.close();  
                JOptionPane.showMessageDialog(null,"Guardado con Exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"File not found:"+e.getMessage());
        }
        }catch(IOException err){
            JOptionPane.showMessageDialog(null,"error on IO operation:"+err.getMessage());
        } 
       return null;   
    }//WriteFile   
}//class

