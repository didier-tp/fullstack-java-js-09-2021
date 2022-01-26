"use strict";
var Personne = /** @class */ (function () {
    function Personne(prenom, nom, age) {
        if (prenom === void 0) { prenom = "?"; }
        if (nom === void 0) { nom = "?"; }
        if (age === void 0) { age = 0; }
        this.prenom = prenom;
        this.nom = nom;
        this.age = age;
    }
    Personne.prototype.fullName = function () {
        return this.prenom + " " + this.nom;
    };
    return Personne;
}());
var p;
p = new Personne("didier", "Defrance", 52);
console.log(p.fullName());
console.log(JSON.stringify(p));
