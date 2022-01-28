import { Component, OnInit } from '@angular/core';
import { Compte } from '../common/data/compte';
import { OrdreVirement } from '../common/data/ordreVirement';
import { ComptesService } from '../common/service/comptes.service';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.scss']
})
export class ComptesComponent implements OnInit {

  numClient : number | undefined;
  listeComptes : Compte[] = [];

  ordreVirement : OrdreVirement = new OrdreVirement();

  onVirement(){
  }

  onRecupererComptes(){
    console.log("1. appel de rechercherComptesDuClient$() qui renvoi immédiatement un objet Observable")
    if(this.numClient != undefined)
       this.comptesService.rechercherComptesDuClient$(this.numClient)
           .subscribe({
             next: (comptes : Compte[])=>{ this.listeComptes = comptes; 
                                  console.log("3. reponse récupérée en différé")},
             error : (err)=>{console.log(err);}
             }
           );
    console.log("2. suite éventuelle de onRecupererComptes sans attente")

  }

  constructor(private comptesService : ComptesService) {
    //injection de dépendance via constructeur
   }

  ngOnInit(): void {
  }

}
