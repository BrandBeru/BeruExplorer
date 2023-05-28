package org.beru.ide.beruide.connections.ssh

import com.jcraft.jsch.*
import org.beru.ide.beruide.application.Managers

open class SSHManager{

    constructor(user: String, host: String, port: Int, pass: String){
        session = JSch().getSession(user,host,port)
        session.setPassword(pass)
        session.setConfig("PreferredAuthentications","password")
        session.setConfig("StrictHostKeyChecking","no")

        session.connect()
    }
    constructor()

    var session: Session = null!!
    var sftp: ChannelSftp = null!!

    init {
        Managers.ssh = this
    }

    public fun channelSFTP(){
        sftp = session.openChannel("sftp") as ChannelSftp
        sftp.connect()
    }

}