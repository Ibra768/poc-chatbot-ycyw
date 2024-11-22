import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, Validators} from "@angular/forms";
import {WebsocketService} from "../../services/websocket.service";
import {Message} from "../../interfaces/message.interface";
import {LoginRequest} from "../../../auth/interfaces/loginRequest.interface";
import {SessionInformation} from "../../../auth/interfaces/sessionInformation.interface";
import {AuthService} from "../../../auth/services/auth.service";

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit {

  messages: Message[] = [];

  public form = this.fb.group({
    content: [
      '',
      [
        Validators.required,
      ]
    ]
  });

  constructor(
    private authService: AuthService,
    private webSocketService: WebsocketService,
    private fb: FormBuilder
  ) {
  }

  ngOnInit(): void {
    this.webSocketService.listen(message => {
      console.log(message);
      this.messages.push(message);
    });
  }

  public submit(): void {
    const content = this.form.value.content??null;
    let user = JSON.parse(sessionStorage.getItem('user')??'');
    const msg: Message = {
      username: user ? user.username :null,
      send_at:new Date().toString(),
      content: content,
    };
    this.webSocketService.send(msg);
    this.form.reset({});
  }

  getUsername(): string | null {
    return this.authService.getUsername(); // Appeler la méthode du service
  }
}
