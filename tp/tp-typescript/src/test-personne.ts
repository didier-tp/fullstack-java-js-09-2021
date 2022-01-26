import { Personne } from "./personne";
let p : Personne;
p = new Personne("didier","Defrance",52);
console.log(p.fullName()) ; console.log(JSON.stringify(p));