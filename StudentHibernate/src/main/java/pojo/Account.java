package pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {	
	
		@Id
		private String username;
		private String password;
		private int roleId;
		public Account(String username, String password, int roleId) {
			super();
			this.username = username;
			this.password = password;
			this.roleId = roleId;
		}
		public Account() {
			super();
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getRoleId() {
			return roleId;
		}
		public void setRoleId(int roleId) {
			this.roleId = roleId;
		}
		
		

}
