// Program.java
// João Marcos Costa Salles RA: 489972
// Henrique Teruo Eihara RA: 490016
package ast;

import java.util.*;
import comp.CompilationError;

public class Program {

	public Program(ArrayList<KraClass> classList, ArrayList<MetaobjectCall> metaobjectCallList, 
			       ArrayList<CompilationError> compilationErrorList) {
		this.classList = classList;
		this.metaobjectCallList = metaobjectCallList;
		this.compilationErrorList = compilationErrorList;
	}


	public void genKra(PW pw) {
            String linha;
            for (KraClass item : this.classList){
                item.genKra(pw);
                pw.println();
            }
	}

	public void genC(PW pw) {
            String linha;
            pw.println("#include <malloc.h>");
            pw.println("#include <stdlib.h>");
            pw.println("#include <stdio.h>\n");
            pw.println("typedef int boolean;");
            pw.println("#define true 1");
            pw.println("#define false 0\n"); 
            pw.println("typedef"); 
            pw.println("   void (*Func)();"); 
            for (KraClass item : this.classList){
                item.genC(pw);
                pw.println();
            }
            
            pw.println("int main () { ");
                pw.add();
                pw.printIdent("_class_Program *program;\n");
                pw.printIdent("program = new_Program();\n");
                pw.printIdent("( ( void ( * ) (_class_Program *) ) program->vt[0] ) (program);\n");
                pw.printIdent("return 0;");
                pw.println("\n}\n");
	}
	
	public ArrayList<KraClass> getClassList() {
		return classList;
	}


	public ArrayList<MetaobjectCall> getMetaobjectCallList() {
		return metaobjectCallList;
	}
	

	public boolean hasCompilationErrors() {
		return compilationErrorList != null && compilationErrorList.size() > 0 ;
	}

	public ArrayList<CompilationError> getCompilationErrorList() {
		return compilationErrorList;
	}

	
	private ArrayList<KraClass> classList;
	private ArrayList<MetaobjectCall> metaobjectCallList;
	
	ArrayList<CompilationError> compilationErrorList;

	
}