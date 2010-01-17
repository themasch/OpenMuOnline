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

/**
 *
 * @author masch
 */
public interface IMessage {

    public ByteArray getData();
    public void setData(ByteArray data);
    public void setData(byte[] data);

    public int getLenght();
    public ByteArray getAction();
    public ByteArray get();

    public void setAction(byte one, byte two);
    public void setAction(int one, int two);

    @Override
    public String toString();
}
