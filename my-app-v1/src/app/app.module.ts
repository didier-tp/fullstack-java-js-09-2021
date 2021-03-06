import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BasicComponent } from './basic/basic.component';
import { CalculatriceComponent } from './basic/calculatrice/calculatrice.component';
import { XyComponent } from './basic/xy/xy.component';
import { ZzComponent } from './basic/zz/zz.component';
import { FooterComponent } from './footer/footer.component';
import { HeaderComponent } from './header/header.component';
import { TvaComponent } from './basic/tva/tva.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { ComptesComponent } from './comptes/comptes.component';
import { VirementComponent } from './virement/virement.component';
import { TogglePanelComponent } from './toggle-panel/toggle-panel.component';
import { TabsModule } from 'ngx-bootstrap/tabs';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    BasicComponent,
    CalculatriceComponent,
    XyComponent,
    ZzComponent,
    TvaComponent,
    WelcomeComponent,
    ComptesComponent,
    VirementComponent,
    TogglePanelComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule , 
    TabsModule.forRoot(),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
