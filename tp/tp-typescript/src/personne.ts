class Personne{
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

let p : Personne;
p = new Personne("didier","Defrance",52);
console.log(p.fullName());
console.log(JSON.stringify(p));
