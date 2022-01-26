"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var personne_1 = require("./personne");
var p;
p = new personne_1.Personne("didier", "Defrance", 52);
console.log(p.fullName());
console.log(JSON.stringify(p));
