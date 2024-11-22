import { Injectable, OnDestroy } from '@angular/core';
import {CompatClient, Stomp, StompSubscription} from '@stomp/stompjs';
import {Message} from "../interfaces/message.interface";
import {AuthService} from "../../auth/services/auth.service";

export type ListenerCallBack = (message: Message) => void;

@Injectable({
  providedIn: 'root'
})
export class WebsocketService implements OnDestroy{

  private readonly connection: CompatClient | undefined = undefined;

  private subscription: StompSubscription | undefined;

  constructor(private authService: AuthService) {
    const token = this.authService.getToken();
    this.connection = Stomp.client('ws://localhost:3001/ws');
    this.connection.connect({ Authorization: `Bearer ${token}` }, () => {});
  }

  public send(message: Message): void {
    if (this.connection && this.connection.connected) {
      console.log(JSON.stringify(message));
      this.connection.send('/app/send', {}, JSON.stringify(message));
    }
  }

  public listen(fun: ListenerCallBack): void {
    if (this.connection) {
      this.connection.connect({}, () => {
        this.subscription = this.connection!.subscribe('/topic/messages', message => fun(JSON.parse(message.body)));
      });
    }
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

}
