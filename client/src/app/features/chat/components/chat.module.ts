import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ChatComponent} from "./chat/chat.component";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {WebsocketService} from "../services/websocket.service";

@NgModule({
  declarations: [ChatComponent],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
  ],
  exports: [ChatComponent],
  providers:[WebsocketService]
})
export class ChatModule {}
