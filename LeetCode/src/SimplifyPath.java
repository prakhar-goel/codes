import java.util.Stack;


public class SimplifyPath {

	public String simplifyPath(String path) {
        
        String[] pathTok = path.split("/");
        
        int n = pathTok.length;
        if(n == 0) return "/";
        
        Stack<String> st = new Stack<String>();
        
        for(int i = 0; i < n; ++i)
        {
            if(pathTok[i].equals(".."))
            {
                if(!st.isEmpty())
                    st.pop();
            }
            else if(!pathTok[i].equals(".") && pathTok[i].length() > 0)
            {
                st.push(pathTok[i]);//System.out.println("sending :" + pathTok[i]);
            }
        }
        
        String res = "";
        if(!st.isEmpty())
        {
        	res = st.pop();
        	while(!st.isEmpty())
                res = st.pop()+"/"+res;
        	res = "/"+res;
        }
        return res;
    }

	public static void main(String[] args) {
		
		SimplifyPath obj = new SimplifyPath();
		System.out.println(obj.simplifyPath("///../../F/./rVH/jmkyl/wpxS/sRC/cL/NR///tO/.//"));

	}

}
