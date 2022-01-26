export class Personne{

    adresse : string = "1 rue Elle 75001 Par ici";

    constructor(public prenom:string="?" ,
                public nom:string="?",
                public age:number=0 ){
    }

    fullName() : string {
      return this.prenom + " " + this.nom;
    }
}

/*
export class Personne{
    prenom :string ;
    nom :string ;
    age :number;

    constructor(prenom:string="?" ,nom:string="?",age:number=0 ){
       this.prenom = prenom;   this.nom = nom;   this.age = age;
    }

    fullName() : string {
      return this.prenom + " " + this.nom;
    }
}
*/


