import { Component, OnInit } from '@angular/core';
import { Compte } from '../common/data/compte';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.scss']
})
export class ComptesComponent implements OnInit {

  numClient : number=0; //à saisir
  listeComptes : Compte[]=[]; //à afficher.

  onRechercherComptesDuClient(){

  }

  constructor() { }

  ngOnInit(): void {
  }

}
