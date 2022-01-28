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
  message:string ="virement pas encore effectué";
  virementOk : boolean = false;

  ordreVirement : OrdreVirement = new OrdreVirement();

  onVirement(){
    this.comptesService.effectuerVirement$(this.ordreVirement)
           .subscribe({
             next: (ordreVirementExecute : OrdreVirement)=>{
                this.message = ordreVirementExecute.message; 
                this.virementOk = ordreVirementExecute.ok?true:false;
                this.onRecupererComptes(); //pour raffraîchir valeurs des comptes 
                //modifiées après virement
             },
             error : (err)=>{console.log(err); this.message ="echec virement"}
             }
           );
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
