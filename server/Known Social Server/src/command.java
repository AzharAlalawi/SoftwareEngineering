
public class command implements java.io.Serializable{
	
	private String wrappedCommand;
	private int wrappedCommandID;
	private commandParameter parameters;
	
	
	
	public command (int commandID, String command, commandParameter param)
	{
		wrappedCommand = command;
		wrappedCommandID = commandID;
		parameters = param;
	}
	
	
	public Object getParameters() {
		return parameters;
	}
	public void setParameters(commandParameter parameters) {
		this.parameters = parameters;
	}
	
	public void setCommand(String command) {
		wrappedCommand = command;
	}
	
	public String getCommand() {
		return wrappedCommand;
	}
	
	
	
	public void setCommandID(int commandID) {
		wrappedCommandID =  commandID;
	}
	
	public int getCommandID() {
		return wrappedCommandID;
	}
	
	
	

}
