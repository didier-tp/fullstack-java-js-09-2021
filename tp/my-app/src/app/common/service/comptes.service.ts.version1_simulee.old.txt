import { Injectable } from '@angular/core';
import {  Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { Compte } from '../data/compte';

@Injectable({
  providedIn: 'root'
})
export class ComptesService {

  //valeurs en dur pour pré-version simulée.
  private listeComptes : Compte[] = [
    new Compte(1,"compteA",120.0),
    new Compte(2,"compteB",50.0)
  ];

  rechercherComptesDuClient$(numClient :number) :Observable<Compte[]> {
    return of(this.listeComptes)
           .pipe(
             delay(330) //simuler un temps d'attente de 330 ms
           );
  }

  constructor() { }
}
