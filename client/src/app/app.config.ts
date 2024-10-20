import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { provideZoneChangeDetection } from '@angular/core';
import { UnauthGuard } from './guards/unauth.guard';
import { AuthService } from './features/auth/services/auth.service';
import { routes } from './app.routes';
import { importProvidersFrom } from '@angular/core';
import { AuthModule } from './features/auth/auth.module';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

export const appConfig = {
  providers: [
    provideHttpClient(),
    provideRouter(routes),
    provideZoneChangeDetection({ eventCoalescing: true }),
    UnauthGuard,
    AuthService,
    importProvidersFrom(AuthModule, BrowserAnimationsModule)
  ]
};
