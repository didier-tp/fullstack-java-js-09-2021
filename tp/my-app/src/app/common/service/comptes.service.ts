import { Injectable } from '@angular/core';
import {  Observable, of } from 'rxjs';
import { delay, map, tap } from 'rxjs/operators';
import { Compte } from '../data/compte';
import { HttpClient } from '@angular/common/http';
import { OrdreVirement } from '../data/ordreVirement';

@Injectable({
  providedIn: 'root'
})
export class ComptesService {

  //baseUrl = "http://localhost:8080/jeeApp/bank-api/compte"; //avec autorisations CORS coté serveur
  baseUrl = "/bank-api/compte"; //url incomplète (relative) possible avec une configuration
  // "reverse-proxy" (ng serve --proxy-conf proxy.conf.json)

  constructor(private http : HttpClient) { }

  rechercherComptesDuClient$(numClient :number) :Observable<Compte[]> {
    let url = this.baseUrl + `?numClient=${numClient}`;
    return this.http.get<Compte[]>(url);
    /*
    return this.http.get<Compte[] >(url , {observe: 'response'})
    .pipe(
      tap(
        response => { console.log("status=" + response.status + " date="  +response.headers.get('date'));} 
      ),
      map( (response)  => response.body?response.body:[])
    );
    */
  }

  effectuerVirement$(ordreVirement : OrdreVirement) :Observable<OrdreVirement> {
    let url = this.baseUrl + `/virement`;
    return this.http.post<OrdreVirement>(url,ordreVirement);
  }
  
}
