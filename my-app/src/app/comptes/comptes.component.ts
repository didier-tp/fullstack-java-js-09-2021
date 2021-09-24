import { Component, OnInit } from '@angular/core';
import { Compte } from '../common/data/compte';
import { CompteService } from '../common/service/compte.service';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.scss']
})
export class ComptesComponent implements OnInit {

  numClient : number=0; //à saisir
  listeComptes : Compte[] = []; //à afficher.

  onRechercherComptesDuClient(){
      this._compteService.rechercherComptesSelonNumClient$(this.numClient)
         .subscribe( 
           (tCompte : Compte[])=>{ this.listeComptes = tCompte },
           (err)=>{ console.log(err) }
          );
  }

  //injection de dépendance
  constructor(private _compteService : CompteService) { }

  ngOnInit(): void {
  }

}
