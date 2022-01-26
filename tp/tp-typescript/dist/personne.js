"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.Personne = void 0;
var Personne = /** @class */ (function () {
    function Personne(prenom, nom, age) {
        if (prenom === void 0) { prenom = "?"; }
        if (nom === void 0) { nom = "?"; }
        if (age === void 0) { age = 0; }
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
        this.adresse = "1 rue Elle 75001 Par ici";
    }
    Personne.prototype.fullName = function () {
        return this.prenom + " " + this.nom;
    };
    return Personne;
}());
exports.Personne = Personne;
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
