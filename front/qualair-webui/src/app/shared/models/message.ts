import {IUser} from "./userModel/user";

export interface Message {
    id?: number
    author: IUser;
    threadId: number;
    reactions: string[];
    content: string
}
