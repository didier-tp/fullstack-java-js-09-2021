import { Injectable } from '@angular/core';
import {  Observable, of } from 'rxjs';
import { delay } from 'rxjs/operators';
import { Compte } from '../data/compte';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ComptesService {

  baseUrl = "http://localhost:8080/jeeApp/bank-api/compte";

  constructor(private http : HttpClient) { }

  rechercherComptesDuClient$(numClient :number) :Observable<Compte[]> {
    let url = this.baseUrl + `?numClient=${numClient}`;
    return this.http.get<Compte[]>(url);
  }
  
}
