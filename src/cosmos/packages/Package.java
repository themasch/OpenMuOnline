/*
 * OpenMuOnline - a open source mu online server
 * Copyright (C) 2009 Mark Schmale
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cosmos.packages;

import cosmos.utils.ByteArray;
import cosmos.exceptions.UnknownPackageException;

/**
 *
 * @author masch
 */
public class Package {

    public static IMessage parse(ByteArray in) throws UnknownPackageException
    {
        if(in.size() == 0) return new EmptyMessage();
        if(in.size() < 4) throw new UnknownPackageException();
        byte msg_type = in.get(0);
        switch(msg_type)
        {
            case (byte)0xC1: return new MessageTypeC1(in);
            case (byte)0xC2: return new MessageTypeC2(in);
                    default:  throw new UnknownPackageException();
        }
    }
}
