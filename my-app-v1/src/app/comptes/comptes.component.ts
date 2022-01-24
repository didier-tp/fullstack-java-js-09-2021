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
      //1.debut 
      this._compteService.rechercherComptesSelonNumClient$(this.numClient)
         .subscribe( 
           (tCompte : Compte[])=>{ this.listeComptes = tCompte; 
                                  // 3. en différé qd réponse prête
                                  },
           (err)=>{ console.log(err) }
          );
      //2. eventuelle suite de l'execution sans attente
  }

  //injection de dépendance
  constructor(private _compteService : CompteService) { }

  ngOnInit(): void {
  }

}
