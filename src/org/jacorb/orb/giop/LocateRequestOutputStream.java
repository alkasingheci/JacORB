package org.jacorb.orb.connection;

/*
 *        JacORB - a free Java ORB
 *
 *   Copyright (C) 1997-2002  Gerald Brose.
 *
 *   This library is free software; you can redistribute it and/or
 *   modify it under the terms of the GNU Library General Public
 *   License as published by the Free Software Foundation; either
 *   version 2 of the License, or (at your option) any later version.
 *
 *   This library is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *   Library General Public License for more details.
 *
 *   You should have received a copy of the GNU Library General Public
 *   License along with this library; if not, write to the Free
 *   Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

import java.io.*;
import org.omg.GIOP.*;
import org.jacorb.orb.*;

/**
 * @author Gerald Brose, FU Berlin 1999
 * @version $Id$
 *
 */

public class LocateRequestOutputStream
    extends MessageOutputStream
{
    private int request_id = -1;
    
    public LocateRequestOutputStream( byte[] object_key, 
                                      int request_id,
                                      int giop_minor )
    {
        super();

        this.request_id = request_id;

        setGIOPMinor( giop_minor );

        writeGIOPMsgHeader( MsgType_1_1._LocateRequest, giop_minor );

        switch( giop_minor )
        {
            case 0 :
            { 
                // GIOP 1.0 == GIOP 1.1, fall through
            }
            case 1 :
            {
                //GIOP 1.1
                LocateRequestHeader_1_0 req_hdr = 
                    new LocateRequestHeader_1_0( request_id, object_key );
                
                LocateRequestHeader_1_0Helper.write( this, req_hdr );

                break;
            }
            case 2 :
            {
                //GIOP 1.2
                TargetAddress addr = new TargetAddress();
                addr.object_key( object_key );

                LocateRequestHeader_1_2 req_hdr = 
                    new LocateRequestHeader_1_2( request_id, addr );

                LocateRequestHeader_1_2Helper.write( this, req_hdr );

                break;
            }
            default :
            {
                throw new Error( "Unknown GIOP minor: " + giop_minor );
            }
        }
    }
    
    public int getRequestId()
    {
        return request_id;
    }
}






