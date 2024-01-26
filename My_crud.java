import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class My_crud {

	public static void main(String[] args) {
		
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
		String DB_URL = "JDBC:mysql://localhost:3306/crud_db";
		String USER = "root";
		String PASS = "";
		
		try {
			
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			
			
			String query = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(255), email VARCHAR(255), PRIMARY KEY (id))";
			
			stmt.executeUpdate(query);
			
			// Adicionando Usuário
			
			Scanner sc = new Scanner(System.in);
			
			boolean onMenu = true;
			
			do {
				System.out.println("""
						
					[1] - Adicionar Usuario; 
					[2] - Buscar Usuario; 
					[3] - Adicionar Usuario; 
					[4] - Adicionar Usuario; 
					[5] - Fechar
					""");
				System.out.println("Opção:>");
				int opcao = sc.nextInt();
				sc.nextLine();
				
				if(opcao == 5) onMenu = false;
				
				if(opcao == 1) {
					System.out.print("Insira o nome de usuário: ");
					String nome = sc.nextLine();
					
					System.out.print("Insira o Email: ");
					String email = sc.nextLine();
					
					query =  "INSERT INTO USERS(NAME,EMAIL) VALUES('" + nome + "','" + email + "');";
					stmt.execute(query);
				}
				
				if(opcao == 2) {
					System.out.print("Insira o ID do usuário: ");
					int id = sc.nextInt();
					
					query =  "SELECT * FROM USERS WHERE id = " + id;
					ResultSet rs = stmt.executeQuery(query);
					
					if(rs.next()) {
						System.out.println("----------------------------------");
						System.out.println("| ID: " + rs.getInt("id"));
						System.out.println("| Nome: " + rs.getString("name"));
						System.out.println("| Email: " + rs.getString("email"));
						System.out.println("----------------------------------");
					} else {
						System.out.println("USER NOT FOUND.");
					}
				}
				
				
			} while (onMenu);
	
			stmt.close();
			conn.close();
			sc.close();
			
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}

	}

}
