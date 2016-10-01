public class StringSplit {
  /*public static final String EXAMPLE_TEST = "This is my small example "
      + "string, wh%ich I'm going to " + "use: for pattern matching.";
  
  private static final Pattern UNDESIRABLES = Pattern.compile("[\\Q][(){},.:;!?<>%\\E]");

  public static void main(String[] args) {
    System.out.println(EXAMPLE_TEST.matches("\\w.*"));
    String[] splitString = (EXAMPLE_TEST.split("\\s+"));
    System.out.println(splitString.length);// should be 14
    for (String string : splitString) {
      //System.out.println(UNDESIRABLES.matcher(string).replaceAll(""));
    }
    String input = "Hi,X How-how are:any you?";
    String[] parts = input.split("[\\W]");
    System.out.println(parts.length);
    for (String string : parts) {
        System.out.println(string);
      }
    // replace all whitespace with tabs
    //System.out.println(EXAMPLE_TEST.replaceAll("\\s+", "\t"));
  }*/
	
	public String[] split(String text){
		return text.split("[\\W]");
	}
}