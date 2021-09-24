import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Compte } from '../data/compte';

@Injectable({
  providedIn: 'root'
})
export class CompteService {
//pour v1 (simulation)
  private tabComptes = [
    new Compte(1,"compte A",50.0) ,
    new Compte(2,"compte B",70.0) , 
    new Compte(3,"compte C",80.0) 
  ]
 
  //nom de methode se terminant par $ = convention de nommage
  //pour methode retournant un Observable
  public rechercherComptesSelonNumClient$(numCli : number) : Observable<Compte[]>{
      //return of(this.tabComptes); //v1 , simulation
      //let url = "http://localhost:8080/appliJee/bank-api/compte?numClient=" + numCli;
      let url = "./bank-api/compte?numClient=" + numCli;
      //NB: l'url relative ./bank-api/... n'est possible en développement
      //que via ng serve --proxy-config proxy.conf.json
      return this._http.get<Compte[]>(url);
  }

  //injection de dépendance du service technique prédéfini HttpClient
  constructor(private _http : HttpClient) { }
}
