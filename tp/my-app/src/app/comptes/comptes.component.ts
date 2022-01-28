import { Component, OnInit } from '@angular/core';
import { Compte } from '../common/data/compte';
import { ComptesService } from '../common/service/comptes.service';

@Component({
  selector: 'app-comptes',
  templateUrl: './comptes.component.html',
  styleUrls: ['./comptes.component.scss']
})
export class ComptesComponent implements OnInit {

  numClient : number | undefined;
  listeComptes : Compte[] = [];

  onRecupererComptes(){
    //...
  }

  constructor(private comptesService : ComptesService) {
    //injection de dépendance via constructeur
   }

  ngOnInit(): void {
  }

}
