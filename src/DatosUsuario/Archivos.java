package DatosUsuario;

import java.io.*;

import java.util.Scanner;

import arbolScores.ArbolScores;
import arbolScores.NodoScores;
import tree.Global;
/**
 * Clase que maneja la lectura y escritura de archivos.
 * @author Daniela E
 * @author Katherine M
 * @author Isaac G
 * */
public class Archivos {
	/**
	 * Constructor la clase de manejo de archivos.
	 * */
	public Archivos() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Escribe el nodo dado en el fichero de datos de usuario.
	 * @param n = nodo a insertar.
	 * */
	public static void escribirFichero(NodoUsuario nodo) {
		FileWriter flwriter = null;
		try {//adem�s de la ruta del archivo recibe un par�metro de tipo boolean, que le indican que se va a�adir m�s registros 
			flwriter = new FileWriter("src//DatosUsuario//dataRegistro.txt",true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(nodo.getNombre() + ",");
			bfwriter.write(nodo.getApellido() + ",");
			bfwriter.write(nodo.getEmail() + ",");
			bfwriter.write(nodo.getUsername() + ",");
			bfwriter.write(nodo.getContrasena() +"\n");
			bfwriter.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	/**
	 * Lee un fichero binario de forma secuencial, y agrega los datos obtenidos al arbol binario que maneja los datos de usuario.
	 * @return usuarios = arbol conteniendo los usuarios
	 */
	public static ArbolUsuario leerFichero() {
		
		File file = new File("src//DatosUsuario//dataRegistro.txt");
		ArbolUsuario usuarios = new ArbolUsuario();	
		Scanner scanner;
		try {
			//se pasa el flujo al objeto scanner
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				// el objeto scanner lee linea a linea desde el archivo
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);
				//se usa una expresi�n regular que valida que antes o despues de una coma (,) exista cualquier cosa			
				delimitar.useDelimiter("\\s*,\\s*");
				NodoUsuario nodo= new NodoUsuario();
				nodo.setNombre(delimitar.next());
				nodo.setApellido(delimitar.next());
				nodo.setEmail(delimitar.next());
				nodo.setUsername(delimitar.next());
				nodo.setContrasena(delimitar.next());
				usuarios.insertarElementoPrimero(nodo);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return usuarios;
		
	}
	
	
	/**
	 * Escribe el nodo dado en el fichero de puntajes.
	 * @param n = nodo a insertar.
	 * @param username = nombre de usuario.
	 * */
	public static void escribirFicheroScore(NodoScores nodo, String username) {
		FileWriter flwriter = null;
		try {//adem�s de la ruta del archivo recibe un par�metro de tipo boolean, que le indican que se va a�adir m�s registros 
			flwriter = new FileWriter("src//arbolScores//"+username+".txt",false);//false para poder sobreescribir
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(nodo.getUsername()+ ",");
			bfwriter.write(nodo.getMov1() + ",");
			bfwriter.write(nodo.getMov2() + ",");
			bfwriter.write(nodo.getHiscore() + ",");
			bfwriter.write(nodo.getSumamax() +"\n");
			bfwriter.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}	
	
	/**
	 * Crea el archivo de puntajes del usuario dado.
	 * @param username = nombre de usuario.
	 * */
	public static void crearArchivo(String username){
	File file = new File("src//arbolScores//"+ username+ ".txt");
	
	try   
	{  
	file.createNewFile();  //creates a new file  
	} 
	catch (IOException e)   
	{  
	e.printStackTrace();    //prints exception if any  
	}   }  
	
	
	/**
	 * Lee el fichero de puntajes de un usuario dado.
	 * Agrega los datos obtenidos al arbol de puntajes.
	 * @param username = nombre de usuario.
	 * @return scores = album de puntajes.
	 * */
	public static ArbolScores leerFicheroScore(String username) {
		
		File file = new File("src//arbolScores//"+ username+ ".txt");
		 
		
		ArbolScores scores = new ArbolScores();	
		Scanner scanner;
		try {
			//se pasa el flujo al objeto scanner
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				// el objeto scanner lee linea a linea desde el archivo
				String linea = scanner.nextLine();
				Scanner delimitar = new Scanner(linea);
				//se usa una expresi�n regular que valida que antes o despues de una coma (,) exista cualquier cosa			
				delimitar.useDelimiter("\\s*\n\\s*");
				NodoScores nodo= new NodoScores();
				nodo.setUsername(delimitar.next());
				nodo.setMov1(delimitar.next());
				nodo.setMov2(delimitar.next());
				nodo.setHiscore(Integer.valueOf(delimitar.next()));
				nodo.setSumamax(Integer.valueOf(delimitar.next()));
				scores.insertarElementoPrimero(nodo);
				delimitar.close();
			}
			scanner.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return scores;
		
	}
	
	/**
	 * Escribe en un fichero los dos ultimos movimientos para sobreescribirlos en dataScores.txt
	 * @param nodo = nodo del dato a reemplazar
	 * @param username = nombre de usuario
	 */
	public static void escribirFicheroActual(NodoScores nodo,String username) {
		FileWriter flwriter = null;
		try {//adem�s de la ruta del archivo recibe un par�metro de tipo boolean, que le indican que se va a�adir m�s registros 
			flwriter = new FileWriter("src//arbolScores//"+username+".txt",false);//false permite sobreescribir la linea
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write(nodo.getUsername()+ "\n");
			bfwriter.write(nodo.getMov1() + "\n");
			bfwriter.write(nodo.getMov2() + "\n");
			bfwriter.write(nodo.getHiscore() + "\n");
			bfwriter.write(nodo.getSumamax() +"\n");
			bfwriter.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Sobreescribe la linea cada movimiento
	 * @param linea = linea que sobreescrible
	 * @param reemplazar = linea a sobreescribir
	 * @param username = nombre de usuario
	 * */
	
	public static void replaceLines(String linea, String reemplazar, String username) {
	    try {
	        // input the (modified) file content to the StringBuffer "input"
	        BufferedReader file = new BufferedReader(new FileReader("src//arbolScores//"+username+".txt"));
	        StringBuffer inputBuffer = new StringBuffer();

	        while ((reemplazar = file.readLine()) != null) {
	            reemplazar = linea; // replace the line here
	            inputBuffer.append(reemplazar);
	            inputBuffer.append('\n');
	        }
	        file.close();

	        // write the new string with the replaced line OVER the same file
	        FileOutputStream fileOut = new FileOutputStream("src//arbolScores//"+username+".txt");
	        fileOut.write(inputBuffer.toString().getBytes());
	        fileOut.close();

	    } catch (Exception e) {
	        System.out.println("Error leyendo el archivo.");
	    }
	}
	
	
	/**
	 * Sobreescribe lineas en el fichero de un usuario dado.
	 * @param nodo = nodo con los datos actuales.
	 * @param reinicio = verifica si el tablero se est� reiniciando o no.
	 * @param username = nombre de usuario.
	 * */
	public static void sobreescribir(NodoScores nodo, boolean reinicio,String username){
		ArbolScores arbol=leerFicheroScore(username);
		boolean flag = false;
		NodoScores aux = arbol.getPrimero();
		String linea="", linea2=username+"\n"+"1-2\n"+Global.score.getText()+"\n"+Global.maximoN.getText()+"\n";
		while(aux != null){
			if (username.equals(aux.getUsername())){
					//linea=nodo.getUsername()+"\n"+"1-2"+"\n"+nodo.getHiscore()+"\n"+nodo.getSumamax()+"\n";
					linea="";
					flag=true;
					break;
				
			}
			aux = aux.getRight(); 
		}
		
		if (flag){
			if (!reinicio){
				//linea2=nodo.getUsername()+","+Global.estadoTablero()+","+nodo.getMov1()+","+Global.score.getText()+","+Global.maximoN+"\n";
				linea2="hola";
			}
			replaceLines(linea2,linea,username);
		}
	}
	
	public static boolean archivoVacio(){
		File newFile = new File("archivo.txt"); 
		if (newFile.length() == 0) { return true;} //archivo vacio
		else { return false; }//archivo no vacio

	}
	
	public static boolean archivoExiste(){
		File tmpDir = new File("ubicacion de archivo");
		return tmpDir.exists();
	}
	
	
}
