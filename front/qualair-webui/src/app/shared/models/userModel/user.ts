import { Role } from './role';
export interface IUser {
    id: number;
    firstName: string;
    lastName: string;
    email: string ;
    role: Role;
}