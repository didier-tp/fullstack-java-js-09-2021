import { Personne } from "./personne";


var p1 : Personne;
//p1 = new Personne();
p1 = new Personne(3,"toto");
p1.numero = 4; //declenche set numero()
console.log(p1);