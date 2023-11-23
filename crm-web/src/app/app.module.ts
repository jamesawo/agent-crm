import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

import {CoreModule} from './@core/core.module';
import {ThemeModule} from './@theme/theme.module';
import {NB_ROOT_MODULES} from './app-nb-modules';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

@NgModule({
    declarations: [AppComponent],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        AppRoutingModule,
        ...NB_ROOT_MODULES,
        CoreModule.forRoot(),
        ThemeModule.forRoot(),
    ],
    bootstrap: [AppComponent],
})
export class AppModule {
}
