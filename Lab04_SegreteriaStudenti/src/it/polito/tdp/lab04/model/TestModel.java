package it.polito.tdp.lab04.model;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		
		int count=0;
		
		/*System.out.println("\n \n \n");
		for(Studente s:model.listaStudenti())
		{
			count++;
		System.out.println(s);	
		}*/
		
	
		
		Corso c=new Corso("Ingegneria della qualità","01NBAPG",8,1);
		for(Studente s:model.getIscritti(c))
		{
		count++;
		System.out.println(s);
			
		}
		
		System.out.println(count);
		

	}

}
