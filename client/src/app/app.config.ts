import {HTTP_INTERCEPTORS, provideHttpClient} from '@angular/common/http';
import { provideRouter } from '@angular/router';
import {LOCALE_ID, provideZoneChangeDetection} from '@angular/core';
import { UnauthGuard } from './guards/unauth.guard';
import { AuthService } from './features/auth/services/auth.service';
import { routes } from './app.routes';
import { importProvidersFrom } from '@angular/core';
import { AuthModule } from './features/auth/auth.module';
import {ChatModule} from "./features/chat/components/chat.module";
import {registerLocaleData} from "@angular/common";
import localeFr from '@angular/common/locales/fr';

registerLocaleData(localeFr);

export const appConfig = {
  providers: [
    provideHttpClient(),
    provideRouter(routes),
    provideZoneChangeDetection({ eventCoalescing: true }),
    UnauthGuard,
    AuthService,
    importProvidersFrom(AuthModule,ChatModule),
    { provide: LOCALE_ID, useValue: 'fr-FR' } // Définir la locale
  ]
};
